#!/usr/bin/env python3

import argparse
import csv
import collections
import logging
from random import sample, seed
import matplotlib.pyplot as plt
from workloads import weibull_generator

from discrete_event_sim import Simulation, Event

# Event to get lengths, to obtain statistics used in plots
class GetLengths(Event):
    def __init__(self, len_schedule):
        self.len_schedule = len_schedule

    def process(self, sim: "MMN"):
        sim.q_lengths = [sim.queue_len(i) for i in range(sim.n)]
        sim.w_track.append((sum(sim.q_lengths) / len(sim.q_lengths)) / sim.lambd)

        for length in sim.q_lengths:
            while len(sim.queue_count) <= length:
                sim.queue_count.append(0)  # add zero counts for new lengths
            sim.queue_count[length] += 1 # counts how many queues have the same length at that moment

        sim.schedule(self.len_schedule, self)


class MMN(Simulation):

    def __init__(self, extension, lambd, mu, n, d, shape, len_schedule, w_track, queue_count):
        super().__init__()

        self.extension = extension # enable extension (default: 0 -> disabled)

        # queues and running jobs
        self.running : list[int] = [None] * n  # if not None, the id of the running job
        self.queues : list[collections.deque] = [collections.deque() for _ in range(n)]  # FIFO queue of the system
        self.arrivals : dict[int, float] = {}  # dictionary mapping job id to arrival time
        self.completions : dict[int, float] = {}  # dictionary mapping job id to completion time
        
        # statistics for the queue lengths
        self.q_lengths : list[int] = [0 for _ in range(n)]  # dictionary mapping job id to queue length
        self.len_schedule = len_schedule
        self.queue_count = queue_count
        self.w_track = w_track

        # basic statistics
        self.lambd : float = lambd
        self.n : int = n
        self.d : int = d
        self.mu : float = mu
        self.arrival_rate : float = lambd * n
        self.completion_rate : float = mu

        # Weibull distribution
        self.shape = shape
        self.arrival_gen = weibull_generator(self.shape, 1 / self.arrival_rate)
        self.completition_gen = weibull_generator(self.shape, 1 / self.completion_rate)

        # schedule the first arrival and the first GetLengths event
        self.schedule(0, Arrival(0, self.supermarket()))
        self.schedule(0, GetLengths(len_schedule))

    def supermarket(self, ifMax = False) -> int:
        # choose d random queues by their indexes
        indexes = sample(range(len(self.queues)), self.d)
        # choose the queue with the minimum length
        if ifMax:
            return max(indexes, key=lambda i: self.queue_len(i)) # added for extension
        return min(indexes, key=lambda i: self.queue_len(i)) # take min based on len of queues

    def schedule_arrival(self, job_id):
        # schedule the arrival following a weibull or exponential distribution (if shape = 1 -> exponential distribution)
        self.schedule(self.arrival_gen(), Arrival(job_id, self.supermarket()))

    def schedule_completion(self, job_id, queue_idx):
        # schedule the time of the completion event following a weibull or exponential distribution (if shape = 1 -> exponential distribution)
        self.schedule(self.completition_gen(), Completion(job_id, queue_idx))

    def queue_len(self, index):
        return (self.running[index] is not None) + len(self.queues[index])

class Arrival(Event):

    def __init__(self, job_id, queue_index):
        self.id = job_id
        self.queue_index = queue_index

    def process(self, sim: MMN):
        # set the arrival time of the job
        sim.arrivals[self.id] = sim.t
        # if there is no running job, assign the incoming one and schedule its completion
        if sim.running is not None and sim.running[self.queue_index] is None:
            sim.running[self.queue_index] = self.id
            sim.schedule_completion(self.id, self.queue_index)
        # otherwise put the job into the queue
        else:
            sim.queues[self.queue_index].append(self.id)
        # schedule the arrival of the next job
        sim.schedule_arrival(self.id + 1)

class Completion(Event):
    def __init__(self, job_id, queue_index):
        self.id = job_id  # currently unused, might be useful when extending
        self.queue_index = queue_index

    def process(self, sim: MMN):
        assert sim.running[self.queue_index] is not None

        # set the completion time of the running job
        sim.completions[sim.running[self.queue_index]] = sim.t
        
        # if the queue is not empty
        if len(sim.queues[self.queue_index]) > 0:
            # get a job from the queue
            sim.running[self.queue_index] = sim.queues[self.queue_index].popleft()
            # schedule its completion
            sim.schedule_completion(sim.running[self.queue_index], self.queue_index)
        elif sim.extension:
            ##----------------- EXTENSION -- Decrease load of the most loaded queue -----------------##
            # if the queue is empty, request a job from the most loaded queue
            max_queue = sim.supermarket(ifMax=True)
            if max_queue != self.queue_index and len(sim.queues[max_queue]) > 0:  # if is not the same queue and the most loaded queue is not empty
                sim.running[self.queue_index] = sim.queues[max_queue].popleft()
                sim.schedule_completion(sim.running[self.queue_index], self.queue_index)
            else:  # if the most loaded queue is also the same as the current one, remain idle
                sim.running[self.queue_index] = None
            ##-----------------------------------------------------------------------------------------------##
        else:
            sim.running[self.queue_index] = None
        
def main():
    parser = argparse.ArgumentParser()
    parser.add_argument('--lambd', type=float, default=0.0)
    parser.add_argument('--mu', type=float, default=1)
    parser.add_argument('--max-t', type=float, default=1_000_000)
    parser.add_argument('--n', type=int, default=1)
    parser.add_argument('--d', type=int, default=1)
    parser.add_argument('--t', type=int, default=0) # note: theoretical plots make graph and legend verbose
    parser.add_argument('--csv', help="CSV file in which to store results")
    parser.add_argument("--seed", help="random seed")
    parser.add_argument("--verbose", action='store_true')
    parser.add_argument("--extension", type=int, default=0) # enable extension (default: 0 -> disabled)
    # choose the rate of the schedule of the queue length event
    parser.add_argument("--len-schedule", type=int, default=200)
    # for weibull distribution
    parser.add_argument('--shape', type=float, default=1)  # if shape = 1 -> exponential distribution
    args = parser.parse_args()

    if args.seed:
        seed(args.seed) # set a seed to make experiments repeatable
    if args.verbose:
        logging.basicConfig(format='{levelname}:{message}', level=logging.INFO, style='{')  # output info on stdout

    # Modify this lines to specify multiple lambda values
    if args.lambd != 0.0:
        lambdas = [args.lambd]
    else:
        lambdas = [0.5, 0.9, 0.95, 0.99]

    pre_allocated = 10 # other queue lenghts are eventually added on the fly
    w_track = []  # Initialize an empty list to store all lengths for Wt computation

    # Creating csv file
    if args.csv is not None:
            with open(args.csv, 'a', newline='') as f:
                writer = csv.writer(f)
                writer.writerow(['Lambda', 'Mu', 'Max time of simulation', 'Average time spent', 'Theoretical avg time spent', 'N of queues', 'D choice'])
 
    for lambd in lambdas:
        sim = MMN(args.extension, lambd, args.mu, args.n, args.d, args.shape, args.len_schedule, w_track, queue_count=[0] * pre_allocated)
        sim.run(args.max_t)

        completions = sim.completions

        # practical W: average time spent in queue by a job
        W = (sum(completions.values()) - sum(sim.arrivals[job_id] for job_id in completions)) / len(completions)
        print(f"Average time spent in the system for lambda={lambd}: {W}") 

        # theoritical W (W = L / lambda for Little's law)
        Wt = (sum(sim.q_lengths) / len(sim.q_lengths)) / lambd
        print(f"Theoretical expectation for random server choice: { Wt }") 

        # Calculate Wt based on all the queue lengths stored in the list
        Wt = sum(w_track) / len(w_track)
        print(f"Theoretical expectation for random server choice using avg in list: { Wt }")

        q_lengths = list(range(len(sim.queue_count)))
        
        # comment for less verbose operation
        # prints the number of queues that have had that length (given by the index) during the simulation
        # print(sim.queue_count)
        
        sum_tot = sum(sim.queue_count) # sum of all the queue counts
        fractions = [] # aux list of fractions 

        # computing the sum of all the queues with length greater or equal to i and dividing by the sum of all the queues
        for i in range(len(sim.queue_count)): # for each queue length
            acc = sum_tot # reset the value to the sum of all the queues
            while i-1 > -1: # proceed removing backwards all the queues with length smaller than i
                acc -= sim.queue_count[i-1] # subtract from the total the number of queues with that length
                i -= 1
            acc /= sum_tot
            fractions.append(acc)

        # Plot the graph for real values
        plt.plot(q_lengths, fractions, label=f'Lambda={lambd}')

        # Plot the graph for theoretical values, if argument t is different from zero
        if args.t != 0:
            # for what we need we expect that the queue length will not be greater than 2 times the pre_allocated value
            queue_range = pre_allocated * 2 
            if sim.d == 1: 
                # given that i is the number of jobs in the system, with d = 1 the fraction of queues with at least i jobs is lambda^i
                plt.plot([i for i in range (1,queue_range)], [lambd ** i for i in range (1,queue_range)], linestyle='dotted', label=f'Theoretical with Lambda={lambd}')
            else:
                # given that i is the number of jobs in the system, with d = 1 the fraction of queues with at least i jobs is lambda^(d^i -1)/(d-1)
                plt.plot([i for i in range (1,queue_range)], [lambd ** (((sim.d ** i)-1)/(sim.d-1)) for i in range (1,queue_range)], linestyle='dotted', label=f'Theoretical with Lambda={lambd}')
 
        if args.csv is not None:
            with open(args.csv, 'a', newline='') as f:
                writer = csv.writer(f)
                writer.writerow([lambd, args.mu, args.max_t, W, Wt, args.n, args.d])

    
    # Set labels, title, grid, and legend
    plt.xlabel('Queue Length (i jobs in the queue)')
    # size i (x axis) is the number of jobs in the queue
    plt.ylabel('Fraction of Queues of at least size i')
    plt.title(f"choices d={sim.d}")
    plt.grid(True)
    plt.legend()
    plt.ylim(0.0, 1.0)
    plt.xlim(1, 14)
    plt.show()

if __name__ == '__main__':
    main()