
flag = True

def coin_change(x, coins, S):
    global flag
    ck = coins.pop(0)
    while x >= ck:
        x -= ck
        S.append(ck)
    for k in range(len(coins)):
        coin_change(x, coins[k:], S.copy())
    if x == 0 and flag:
        flag = False
        print(S)

def greedy_coin_change(x, coins):
    S = []
    while x > 0 and len(coins) > 0:
        ck = coins.pop(0)
        while x >= ck:
            x -= ck
            S.append(ck)
    if x == 0: print(S)
    else: print("No solution")

#C = list(map(int, input().strip().split()))
n = int(input())
C = [2, 5, 13, 100]
C.sort(reverse=True)
coin_change(n, C, [])
#greedy_coin_change(n, C)
