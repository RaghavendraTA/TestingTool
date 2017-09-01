#include <iostream>
#include <vector>
using namespace std;

typedef long long lld;

class Pair {
  public:
    lld first;
    lld second;
    Pair(lld f, lld s) {
        first = f;
        second = s;
    }
};

class Graph {
  public:

    vector<Pair> **adjacent;
    lld n, m, totalPaths;

    Graph(lld num) {
        n = num, m = 10000, totalPaths = 0;
        adjacent = new vector<Pair>*[n];
        for(lld i = 0; i < n; i++)
            adjacent[i] = new vector<Pair>;
    }

    void addline(lld v1, lld v2, lld cost) {
        Pair p(v2, cost);
        adjacent[v1]->push_back(p);
    }

    void dijkstra(lld node, lld end, lld cost) {
        if(node == end) {
            this->m = (cost < m) ? cost : m;
            totalPaths++;
        }
        for(auto j = adjacent[node]->begin(); j != adjacent[node]->end(); ++j) {
            dijkstra(j->first, end, cost + j->second);
        }
    }
    
};

int main() {
    
    lld n = 9;

    Graph g(n);
    g.addline(0, 1, 1);
    g.addline(0, 2, 1);
    g.addline(1, 2, 50);
    g.addline(1, 3, 1);
    g.addline(2, 5, 10);
    g.addline(3, 4, 1);
    g.addline(4, 5, 20);
    g.addline(4, 7, 2);
    g.addline(5, 7, 3);
    g.addline(3, 6, 50);
    g.addline(6, 8, 30);
    g.addline(6, 7, 100);
    g.addline(7, 8, 1);

    g.dijkstra(0, 8, 0);
    cout << g.m << " is the lowest cost & " << g.totalPaths << " different paths are available"<< endl;
}
