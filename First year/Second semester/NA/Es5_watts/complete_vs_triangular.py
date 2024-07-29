import networkx as nx
import matplotlib.pyplot as plt

N = 10 # number of nodes
k = 4 # neighbors of each node (watts)
p = 0.1 # probability of rewiring (watts)
# G = nx.complete_graph(N)
G = nx.watts_strogatz_graph(N, k, p)
G_triangular = nx.triangular_lattice_graph(N, N)

# Diameter
if nx.is_connected(G):
    print("Diameter: ", nx.diameter(G))
if nx.is_connected(G_triangular):
    print("Diameter of triangular: ", nx.diameter(G_triangular))

# Compute clustering coefficient
print("Clustering coefficient of complete graph: ", nx.average_clustering(G))
print("Clustering coefficient of triangular lattice: ", nx.average_clustering(G_triangular))

# Number of triangles
print("Number of triangles in complete graph: ", sum(nx.triangles(G).values()) / 3)
print("Number of triangles in triangular lattice: ", sum(nx.triangles(G_triangular).values()) / 3)

# Transitivity
print("Transitivity of complete graph: ", nx.transitivity(G))
print("Transitivity of triangular lattice: ", nx.transitivity(G_triangular))

# Plot the degree distribution
plt.figure()
plt.hist(list(dict(G.degree()).values()), bins=range(N + 2))
plt.title("Degree distribution of complete graph")
plt.show()

# plt.figure()
# plt.hist(list(dict(G_triangular.degree()).values()), bins=range(7))
# plt.title("Degree distribution of triangular lattice")
# plt.show()

# Plot the graph using circular layout
plt.figure()
pos = nx.circular_layout(G)
nx.draw(G, pos, with_labels=False)
plt.title("Complete graph")
plt.show()

# plt.figure()
# nx.draw(G_triangular, with_labels=False)
# plt.title("Triangular lattice")
# plt.show()