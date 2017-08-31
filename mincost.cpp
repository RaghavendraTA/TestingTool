#include <iostream>
#include <cstring>
using namespace std;

int mincost(int *data[], int m, int n) {

    int dp[m][n];
    memset(dp, 0, sizeof(dp[0][0]) * m * n);
    dp[0][0] = data[0][0];

    for(int i=1; i<m; i++)
        dp[i][0] = data[i][0] + dp[i-1][0];
        
    for(int i=1; i<n; i++)
        dp[0][i] = data[0][i] + dp[0][i-1];

    for(int i=1; i<m; i++)
        for(int j=1; j<n; j++) 
            dp[i][j] = min(dp[i][j-1], dp[i-1][j]) + data[i][j];
            
    return dp[m-1][n-1];
}

int main() {
    int m, n;
    cin >> m >> n;
    int *data[m];
    for(int i=0; i<m; i++) {
        data[i] = new int[n];
        for(int j=0; j<n; j++)
            cin >> data[i][j];
    }
    cout << mincost(data, m, n);
    return 0;
}
