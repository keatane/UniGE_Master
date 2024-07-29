import networkx as nx
import matplotlib.pyplot as plt
import statistics as st
import scipy as sp

# G = nx.random_graphs.erdos_renyi_graph(10, 0.1)
# read an external mtx file as graph
G = nx.read_adjlist("power.mtx", create_using=nx.DiGraph)

print("Betweenness centrality in avg: ", st.mean(list(nx.betweenness_centrality(G).values())))
print("Closeness centrality in avg: ", st.mean(list(nx.closeness_centrality(G).values())))

# create a dict with number of node as key and betweenness centrality as value, then sort it from the highest to the lowest
bc = nx.betweenness_centrality(G)
bc = dict(sorted(bc.items(), key=lambda item: item[1], reverse=True))
print("Top 10 bc", bc)

# create a dict with number of node as key and closeness centrality as value, then sort it from the highest to the lowest
cc = nx.closeness_centrality(G)
cc = dict(sorted(cc.items(), key=lambda item: item[1], reverse=True))
print("Top 10 cc", cc)

# visualize the graph
nx.draw(G, with_labels=True)
plt.show()
