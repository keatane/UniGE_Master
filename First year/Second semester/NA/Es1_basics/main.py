import networkx as nx
import matplotlib.pyplot as plt
import statistics as st

G = nx.Graph([{1,2},{4,5},{2,3},{3,4},{1,4},{2,4}])
print(G.nodes())

# compute the diameter and the centrality of the graph
print(nx.diameter(G))
# compute the mean of the betwennes centrality

print("Betweenness centrality: ", st.mean(list(nx.betweenness_centrality(G).values())))

# compute the path between two nodes
print("Shortest path between  and 5: ", nx.shortest_path(G, 1, 5))

# visualize the graph
# nx.draw(G, with_labels=True)
# plt.show()
