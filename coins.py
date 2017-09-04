
def coin_change(x, coins, S):
    ck = coins.pop(0)
    while x >= ck:
        x -= ck
        S.append(ck)
    if x > 0:
        for k in range(len(coins)):
            coin_change(x, coins[k:], S.copy())
    if x == 0:
        print(S)

C = [10, 2, 5]
C.sort(reverse=True)
coin_change(9, C, [])
