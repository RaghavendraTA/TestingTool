from operator import itemgetter

n, W = map(int, input().strip().split())
ls = []
for i in range(n):
    v, w = map(int, input().strip().split())
    ls.append((v, w))
ls.sort(key=operator.itemgetter(0), reverse=True)
wight, mx = 0, 0
for i in ls:
    if wight + i[1] <= W:
        mx += i[0]
        wight += i[1]
    else: 
        break
print(mx)
