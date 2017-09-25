#!/bin/python3

INF = 1000000
 
class Graph():
 
    def __init__(self, G):
        self.V = len(G.keys())
        self.graph = G
            
    def minDistance(self, dist, sptSet):
        min = INF
        for v in range(self.V):
            if dist[v] < min and sptSet[v] == False:
                min = dist[v]
                min_index = v
 
        return min_index
        
    def dijkstra(self, src):
 
        dist = [INF] * self.V
        dist[src] = 0
        sptSet = [False] * self.V
 
        for cout in range(self.V):
            u = self.minDistance(dist, sptSet)
            sptSet[u] = True
            
            for v in range(1, self.V):
                if u not in self.graph.keys() or v not in self.graph[u].keys(): continue
                if self.graph[u][v] > 0 and sptSet[v] == False and dist[v] > dist[u] + self.graph[u][v]:
                        dist[v] = dist[u] + self.graph[u][v]
        
queries = int(input().strip())
G = dict()

for q in range(queries):
    node_1, node_2, dist = map(int, input().strip().split())
    try:
        G[node_1][node_2] = dist
    except:
        G[node_1] = {node_2 : dist}
    try:
        G[node_2][node_1] = dist
    except:
        G[node_2] = {node_1 : dist}

g = Graph(G)
g.dijkstra(0)

'''
Test case 1
14
0 1 4
0 7 8
1 2 8
1 7 11
2 3 7
2 8 2
2 5 4
3 4 9
3 5 14
4 5 10
5 6 2
6 8 6
6 7 1
7 8 7
'''
