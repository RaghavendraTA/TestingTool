import sys

def coin_change(x, coins, S):
    ck = coins[0]
    coins.pop(0)
    while x >= ck:
        x -= ck
        S.append(ck)
    if x > 0:
        for k in range(len(coins)):
            coin_change(x, coins[k:], S.copy())
    if x == 0:
        print(S)

C = [10, 5, 2]
C.sort(reverse=True)
coin_change(20, C, [])
