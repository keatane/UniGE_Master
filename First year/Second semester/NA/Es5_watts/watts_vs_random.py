import networkx as nx
import matplotlib.pyplot as plt

N = 10 # number of nodes
k = 4 # neighbors of each node (watts)
p = 0.1 # probability of rewiring (watts)
G = nx.watts_strogatz_graph(N, k, p)
G2 = nx.gnm_random_graph(N, G.edges().__len__())

# Diameter
if nx.is_connected(G):
    print("Diameter: ", nx.diameter(G))
if nx.is_connected(G2):
    print("Diameter of triangular: ", nx.diameter(G2))

# Compute clustering coefficient
print("Clustering coefficient of  graph: ", nx.average_clustering(G))
print("Clustering coefficient of graph: ", nx.average_clustering(G2))

# Number of triangles
print("Number of triangles in graph: ", sum(nx.triangles(G).values()) / 3)
print("Number of triangles in graph: ", sum(nx.triangles(G2).values()) / 3)

# Transitivity
print("Transitivity of graph: ", nx.transitivity(G))
print("Transitivity of graph: ", nx.transitivity(G2))

# Plot the degree distribution
plt.figure()
plt.hist(list(dict(G.degree()).values()), bins=range(N + 2))
plt.title("Degree distribution of graph")
plt.show()

plt.figure()
plt.hist(list(dict(G2.degree()).values()), bins=range(7))
plt.title("Degree distribution of graph")
plt.show()

# Plot the graph using circular layout
plt.figure()
pos = nx.circular_layout(G)
nx.draw(G, pos, with_labels=False)
plt.title(" graph")
plt.show()

plt.figure()
nx.draw(G2, with_labels=False)
plt.title("graph")
plt.show()