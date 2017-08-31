#include <iostream>
#include <cstring>
using namespace std;

int knapsack(int v[], int w[], int n, int W) {

    int dp[n + 1][W + 1];
    memset(dp, 0, sizeof(dp[0][0]) * (n + 1) * (W + 1));
    
    for(int i = 1; i <= n; i++) {
        for(int wi = 1; wi <= W; wi++) {
            if(w[i] <= wi)
                dp[i][wi] = max(dp[i-1][wi], v[i] + dp[i-1][wi-w[i]]);
            else
                dp[i][wi] = dp[i - 1][wi];
        }
    }
    return dp[n][W];
}

int main() {
    int n, W;
    cin >> n >> W;
    int v[n], w[n];
    for(int i=0; i<n; i++)
        cin >> v[i] >> w[i];
    cout << knapsack(v, w, n, W);
    return 0;
}
/*
4 10
10 5
40 4
30 6
50 3
Should be 90
*/
