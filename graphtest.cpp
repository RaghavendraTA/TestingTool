#include <iostream>
#include <vector>
#include <climits>
using namespace std;

typedef long long lld;
typedef pair<lld, lld> Pair;

class Graph {
  public:

    vector<vector<Pair>> *adjacent;
    lld n;

    Graph() {
        adjacent = new vector<vector<Pair>>;
    }

    void addEdge(lld v1, lld v2, lld cost) {
        vector<Pair> empty_list;
        while(adjacent->size() <= v1)
            adjacent->push_back(empty_list);
        Pair p(v2, cost);
        adjacent->at(v1).push_back(p);
    }
    
    vector<lld> dijkstra(lld s) {

        vector<lld> dist(n + 1, INT_MAX);
        vector<bool> visited(n + 1, false);

        dist[s] = 0;
        visited[s] = true;
        lld u = s;
        lld sd = INT_MAX;
        
        while(true) {
            for(Pair i: adjacent->at(u)) {
                if(dist[i.first] > dist[u] + i.second) {
                    dist[i.first] = dist[u] + i.second;
                }
            }
            visited[u] = true;
            u = -1;
            sd = INT_MAX;
            for(lld i = 0; i < n; i++) {
                if(dist[i] < sd && !visited[i]) {
                    sd = dist[i];
                    u = i;
                }
            }
            if(u == -1) return dist;
        }
        return dist;
    }
    
};

int main() {

    Graph g();
    g.addEdge(0, 1, 1);
    g.addEdge(0, 2, 1);
    g.addEdge(1, 2, 50);
    g.addEdge(1, 3, 1);
    g.addEdge(2, 5, 10);
    g.addEdge(3, 4, 1);
    g.addEdge(4, 5, 20);
    g.addEdge(4, 7, 2);
    g.addEdge(5, 7, 3);
    g.addEdge(3, 6, 50);
    g.addEdge(6, 8, 30);
    g.addEdge(6, 7, 100);
    g.addEdge(7, 8, 1);

    vector<lld> dist = g.dijkstra(0);
    for(lld i : dist) cout << i << " ";
}
