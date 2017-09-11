
r = []

def change(x, coins, S):
    global r
    if x == 0 and len(S) < len(r):
        r = S.copy()
        return
    for i in range(len(coins)):
        rem = x % coins[i]
        t = [coins[i]] * ((x - rem) // coins[i])
        temp = coins.copy()
        temp.pop(i)
        change(rem, temp, t + S)

def coin_change(x, coins):
    global r
    coins.sort(reverse=True)
    r = [0] * (x + 1)
    change(x, coins, [])
    if len(r) == (x + 1):
        return []
    return sorted(r)

'''
C = list(map(int, input().strip().split()))
n = int(input())
print(coin_change(n, C))
'''
