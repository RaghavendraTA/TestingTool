#include <iostream>
#include <vector>
#include <climits>
using namespace std;

typedef long long lld;
typedef pair<lld, lld> Pair;

class Graph {
  public:

    vector<vector<Pair>> *adjacent;
    vector<Pair> empty_list;

    Graph() {
        adjacent = new vector<vector<Pair>>;
    }

    void addEdge(lld v1, lld v2, lld cost) {
        while(adjacent->size() <= v1)
            adjacent->push_back(empty_list);
        Pair p(v2, cost);
        adjacent->at(v1).push_back(p);
    }

    void path_finder(lld node, lld end, lld cost) {
        if(node == end) {
            cout << cost << endl;
            return;
        }
        for(Pair i: adjacent->at(node))
            path_finder(i.first, end, cost + i.second);
    }
    
};

int main() {

    Graph g;
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

    g.path_finder(0, 8, 0);
}
