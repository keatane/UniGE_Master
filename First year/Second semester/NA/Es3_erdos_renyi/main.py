'''We start by generating different Erdős-Rényi random graphs G(N,p) for different values of the parameters, e.g., by varying the number of vertices N or the probability p. 

For each graph, compute some of the metrics we have already computed in previous exercises and check if it is connected. If the answer is yes, compute also its diameter.

Plot the degree distribution.'''

import networkx as nx
import matplotlib.pyplot as plt
import numpy as np
import collections

# Generate a random graph
N = 1000
p = 1/N
#G1 = nx.erdos_renyi_graph(N, p)
#G2 = nx.erdos_renyi_graph(N, p)
#G3 = nx.erdos_renyi_graph(N, p)

def compute_degree(G):
    # Compute the degree distribution
    degree_sequence = sorted([d for n, d in G.degree()], reverse=True)
    degreeCount = collections.Counter(degree_sequence)
    deg, cnt = zip(*degreeCount.items())

    # Plot the degree distribution
    fig, ax = plt.subplots()
    plt.bar(deg, cnt, width=0.80, color='b')
    plt.title("Degree Distribution")
    plt.ylabel("Count")
    plt.xlabel("Degree")
    plt.show()

def compute_metrics(G):
    # Compute the mean of betweenness centrality taking the mean of the nodes
    betweenness_centrality = np.mean(list(nx.betweenness_centrality(G).values()))
    print("Betweenness centrality: ", betweenness_centrality)

    # Compute the closeness centrality
    closeness_centrality = np.mean(list(nx.closeness_centrality(G).values()))
    print("Closeness centrality: ", closeness_centrality)

def check_connection_diameter(G):
    # Check if the graph is connected
    found = 0
    #print("Is G connected? ", nx.is_connected(G))
    if (nx.is_connected(G)):
        found = 1
        #print("Diameter of G: ", nx.diameter(G))
    return found

compute_metrics(G1)
compute_metrics(G2)
compute_metrics(G3)
check_connection_diameter(G1)
check_connection_diameter(G2)
check_connection_diameter(G3)
compute_degree(G1)
compute_degree(G2)
compute_degree(G3)




