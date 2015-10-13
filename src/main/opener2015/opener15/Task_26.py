import Queue

def wall(a, b):
  s = set([tuple(a), tuple(b)])
  p = [405, 45]
  def r(m = 7):
    p[0] = (p[0] / 0x10000) + 36969 * (p[0] % 0x10000)
    p[1] = (p[1] / 0x10000) + 18000 * (p[1] % 0x10000)
    return (p[0] * 0x10000 + p[1]) % m
  def t(v):
    u = v[:]
    u[r()] += 2 * r(2) - 1
    # print(set([tuple(u), tuple(v)]))
    return set([tuple(u), tuple(v)])
  return any(t([r() for _ in xrange(0, 7)])==s for _ in xrange(0, 600000))

# print("wall: ", wall([0, 0, 0, 0, 0, 0, 0], [1, 0, 0, 0, 0, 0, 0]))
# print("one more time...")
# print("wall: ", wall([0, 0, 0, 0, 2, 0, 0], [0, 0, 0, 0, 3, 0, 0]))
# print("wall: ", wall([0, 0, 0, 0, 1, 0, 0], [0, 0, 0, 0, 2, 0, 0]))
# exit(1)

p = [405, 45]
def r(m=7):
  p[0] = (p[0] / 0x10000) + 36969 * (p[0] % 0x10000)
  p[1] = (p[1] / 0x10000) + 18000 * (p[1] % 0x10000)
  return (p[0] * 0x10000 + p[1]) % m

def t(v):
  u = v[:]
  i = r()
  d = 2 * r(2) - 1
  u[i] += d
  return (v, u, i, d)

def dind(i, d):
  return i*2 + (1+d)/2

w = [[[[[[[[True for _ in xrange(14)] for _ in xrange(7)] for _ in xrange(7)] for _ in xrange(7)] for _ in xrange(7)] for _ in xrange(7)] for _ in xrange(7)] for _ in xrange(7)]

# w[0][0][0][0][0][0][0][0] = True;
# print("test: ", w[0][0][0][0][0][0][0][0]);
# print("test: ", w[0][0][0][0][0][0][1][0]);
# exit(1)

def set_wall(u, i, d):
  w[u[0]][u[1]][u[2]][u[3]][u[4]][u[5]][u[6]][dind(i,d)] = False

def has_wall(u, i, d):
  if (u[i] == 0 and d == -1) or (u[i] == 6 and d == 1):
    return True
  return w[u[0]][u[1]][u[2]][u[3]][u[4]][u[5]][u[6]][dind(i,d)]

for steps in xrange(0, 600000):
  a = t([r() for _ in xrange(0, 7)])
  v = a[0]
  u = a[1]
  i = a[2]
  d = a[3]

  # print(set([tuple(u), tuple(v)]))

  if 0 <= u[i] <= 6:
  # if all(0 <= ui <= 6 for ui in u):
    set_wall(v, i, d)
    set_wall(u, i, -d)

# set([(0, 0, 0, 0, 1, 0, 0), (0, 0, 0, 1, 1, 0, 0)])
# set([(0, 0, 0, 0, 1, 0, 0), (0, 0, 0, 0, 0, 0, 0)])
# set([(0, 0, 0, 0, 1, 0, 0), (0, 0, 0, 0, 2, 0, 0)])

# print (has_wall([0, 0, 0, 0, 1, 0, 0], 4, -1));# t
# print (has_wall([0, 0, 0, 0, 1, 0, 0], 4, 1));# t
# print (has_wall([0, 0, 0, 0, 1, 0, 0], 6, 1));# f
# print (has_wall([0, 0, 0, 0, 1, 0, 0], 6, -1));# t
# print (has_wall([0, 0, 0, 0, 1, 0, 0], 3, 1));# t
# print (has_wall([0, 0, 0, 0, 1, 0, 0], 5, 1));# f 
# print (has_wall([0, 0, 0, 0, 1, 0, 0], 5, -1));# t
# exit(1)

count = [[[[[[[-1 for _ in xrange(7)] for _ in xrange(7)] for _ in xrange(7)] for _ in xrange(7)] for _ in xrange(7)] for _ in xrange(7)] for _ in xrange(7)]
fromv = [[[[[[[-1 for _ in xrange(7)] for _ in xrange(7)] for _ in xrange(7)] for _ in xrange(7)] for _ in xrange(7)] for _ in xrange(7)] for _ in xrange(7)]
count[0][0][0][0][0][0][0] = 0

def set_count(v, c):
  count[v[0]][v[1]][v[2]][v[3]][v[4]][v[5]][v[6]] = c

def get_count(v):
  return count[v[0]][v[1]][v[2]][v[3]][v[4]][v[5]][v[6]]

def set_from(v, u):
  fromv[v[0]][v[1]][v[2]][v[3]][v[4]][v[5]][v[6]] = u

def get_from(v):
  return fromv[v[0]][v[1]][v[2]][v[3]][v[4]][v[5]][v[6]]

def get_count(v):
  return count[v[0]][v[1]][v[2]][v[3]][v[4]][v[5]][v[6]]

print("GO")

p0 = [0, 0, 0, 0, 0, 0, 0]
p6 = [6, 6, 6, 6, 6, 6, 6]

q = Queue.Queue()
q.put(p0)

set_count(p0, 0)

while not q.empty():
  v = q.get()
  vc = get_count(v)
  # print(v, vc)

  for i in xrange(0, 7):
    # print("i", i)
    for d in (xrange(-1, 2, 2)):
      # print("d", d)
      if not has_wall(v, i, d):
        # print("no wall: ", i, d)
        u = v[:]
        u[i] += d
        ccc = get_count(u);
        # print('count : ', u, vc, ccc);
        if get_count(u) == -1:
          q.put(u);
          set_from(u, v)
          set_count(u, vc + 1)


print("Path")
last = p6
while (last != -1):
  print(last)
  prev = last
  last = get_from(last)
  # if (last != -1 and wall(last, prev)):
  #   print ("OUCH: ", prev, last)

print(get_count(p6))
