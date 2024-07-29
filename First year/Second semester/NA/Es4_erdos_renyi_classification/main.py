'''
Consider an Erdős-Rényi random graph with N = 3000 nodes, connected to each other with probability p = 10-3 and then answer to the following questions.

What is the expected number of links, 〈L〉?
In which regime is the network?
Calculate the probability pc so that the network is at the critical point.
Given the linking probability p = 10-3, calculate the number of nodes Ncr so that the network has only one component.
For the network in (4), calculate the average degree 〈kcr〉 and the average distance 〈d〉 between two randomly chosen nodes
'''

import networkx as nx
import matplotlib.pyplot as plt
from math import log

# Generate a random graph
N = 3000
p = 10**-3
G = nx.erdos_renyi_graph(N, p)

# Calculate the expected number of links
L = p*N*(N-1)/2
print(f"The expected number of links is {L}")

# Calculate the average degree
k = p*(N-1)
print(f"The average degree is {k}")

# Calculate the regime of the network

print(f"The critical probability is {1/N} while the actual probability is {p}")
if p < 1/N and k < 1:
    print("The network is in the subcritical regime")
elif p == 1/N and k == 1:
    print("The network is in the critical regime")
elif p > 1/N and k > 1:
    print("The network is in the supercritical regime")
else:
    print("The network is in the connected regime")

# Calculate the number of nodes Ncr so that the network has only one component
Ncr = int(1/p) # obtained by reversing the forumla for p > 1/N for a single giant component
print(f"The number of nodes Ncr so that the network has only one component is {Ncr}")

Gcr = nx.erdos_renyi_graph(Ncr, p)
kcr = p*(Ncr-1)
print(f"The average degree for Ncr is {kcr}")

# Calculate the average distance between two randomly chosen nodes
# d = nx.average_shortest_path_length(Gcr) # can't be done since the graph is not connected (critical regime)
d = log(Ncr)
print(f"The average distance between two randomly chosen nodes is {d}")

# Plot the graph
print("Plotting the graph...")
nx.draw(G, node_size=10)
plt.show()
nx.draw(Gcr, node_size=10)
plt.show()
print("End")


