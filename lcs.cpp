#include <iostream>
#include <cstring>
using namespace std;

int main() {

    string target, pattern;
    cin >> target >> pattern;

    int m = pattern.size();
    int n = target.size();
    int dp[m + 1][n + 1];
    memset(dp, 0, sizeof(dp[0][0]) * (m + 1) * (n + 1));

    for(int i=1; i<=m; i++) {
        for(int j=1; j<=n; j++) {
            if(pattern[i-1] == target[j-1])
                dp[i][j] = dp[i-1][j-1] + 1;
            else
                dp[i][j] = max(dp[i][j-1], dp[i-1][j]);
        }
    }
    cout << dp[m][n];
    return 0;
}
