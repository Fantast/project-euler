from math import log
from functools import reduce


def f(n):
  r = []
  for _ in range(n + 1):
    r = reduce((lambda r, e: [r[0] + [r[1] + e], e]), r, [[], 0])[0] + [1]
    # print r

  a = reduce(lambda r, e: r + str(e), map(lambda r: r & 1, r), '')
  print a
  return log(
    pow(
      int(a, 2) - 1,
      2)
    ) / (n + 1)


# def f(n):
#   for _ in range(n + 1):
#     r = reduce(lambda r, e: [r[0] + [r[1] + e], e], 'r' in locals() and r or [], [[], 0])[0] + [1]
#   return log(pow(int(reduce(lambda r, e: r + str(e), map(lambda r: r & 1, r), ''), 2) - 1, 2)) / (n + 1)

print f(32)
print 'Answer:', log(4)

