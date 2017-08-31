
def kanpsack(v, w, n, W):

    dp = [[0] * (W + 1)]
    for i in range(n):
        dp.append([0])

    for i in range(1, n):
        for wi in range(1, W + 1):
            if(w[i] <= wi):
                dp[i].insert(wi, max(dp[i-1][wi], v[i] + dp[i-1][wi - w[i]]))
            else:
                dp[i].insert(wi, dp[i-1][wi])

    return dp[n - 1][W]

#r = kanpsack([60, 100, 120], [10, 20, 30], 3, 50)
r = kanpsack([10, 40, 30, 50], [5, 4, 6, 3], 4, 10)
print(r)
