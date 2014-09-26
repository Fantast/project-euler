package solved;

import tasks.ITask;
import tasks.Tester;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Iterator;
import java.util.TreeSet;

import static java.math.BigInteger.valueOf;
import static utils.MyMath.bi;
import static utils.OtherUtils.intCmp;

//Answer : 328968937309
public class Task_212 implements ITask {
    private final long MOD = 1000000L;
    private final BigInteger BMOD = valueOf(MOD);

    int n = 50000;
    Cube cubes[] = new Cube[50000];

    public class Cube {
        int ind;
        int x; int x2;
        int y; int y2;
        int z; int z2;

        Point xb, xe;
        Point yb, ye;
        Point zb, ze;

        public Cube(int ind, int x, int y, int z, int x2, int y2, int z2) {
            this.ind = ind;
            this.x = x;
            this.x2 = x2;
            this.y = y;
            this.y2 = y2;
            this.z = z;
            this.z2 = z2;

            xb = new Point(x, this, true);
            xe = new Point(x2, this, false);
            yb = new Point(y, this, true);
            ye = new Point(y2, this, false);
            zb = new Point(z, this, true);
            ze = new Point(z2, this, false);
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Cube)) return false;
            Cube oth = (Cube) obj;
            return oth.ind == ind;
        }

        @Override
        public int hashCode() {
            return ind;
        }

        @Override
        public String toString() {
            return "Cube{" +
                   "x=" + x +
                   ", y=" + y +
                   ", z=" + z +
                   ", dx=" + (x2-x) +
                   ", dy=" + (y2-y) +
                   ", dz=" + (z2-z) +
                   '}';
        }
    }

    public static class Point implements Comparable<Point> {
        int val;
        Cube cube;
        boolean begin;

        public Point(int val, Cube cube, boolean begin) {
            this.val = val;
            this.cube = cube;
            this.begin = begin;
        }

        @Override
        public int compareTo(Point oth) {
            if (this == oth) return 0;

            int c = intCmp(val, oth.val);
            if (c != 0) return c;

            if (begin && !oth.begin) {
                return -1;
            }

            if (!begin && oth.begin) {
                return 1;
            }

            return intCmp(cube.ind, oth.cube.ind);
        }
    }

    public void solving() {
        int s[] = new int[300000];
        int x[] = new int[n];
        int y[] = new int[n];
        int z[] = new int[n];
        int dx[] = new int[n];
        int dy[] = new int[n];
        int dz[] = new int[n];
        for (int k = 0; k < 300000; ++k) {
            long K = k + 1;
            s[k] = (int) (k < 55
                                   ? (int) bi(100003L - 200003L * K).add(bi(300007).multiply(bi(K).pow(3))).mod(BMOD).longValue()
                                   : (s[k - 24] + s[k - 55]) % MOD);
        }

        for (int i = 0; i < n; ++i) {
            x[i] = s[6*i] % 10000;
            y[i] = s[6*i + 1] % 10000;
            z[i] = s[6*i + 2] % 10000;
            dx[i] = 1 + (s[6*i + 3] % 399);
            dy[i] = 1 + (s[6*i + 4] % 399);
            dz[i] = 1 + (s[6*i + 5] % 399);

            cubes[i] = new Cube(i, x[i], y[i], z[i], x[i] + dx[i], y[i] + dy[i], z[i] + dz[i]);
        }
        System.out.println(findVolume());
    }

    private long findVolume() {
        Point xs[] = new Point[2*n];
        for (int i = 0; i < n; ++i) {
            xs[2*i] = cubes[i].xb;
            xs[2*i+1] = cubes[i].xe;
        }

        Arrays.sort(xs);
        int xsn = xs.length;

        TreeSet<Point> current = new TreeSet<Point>();
        long volume = 0;

        int lastX = xs[0].val;
        long currentArea = 0;

        int i = 0;
        while (i < xsn) {
            volume += currentArea * (xs[i].val - lastX);
            lastX = xs[i].val;

            do {
                if (xs[i].begin) {
                    current.add(xs[i].cube.yb);
                    current.add(xs[i].cube.ye);
                } else {
                    current.remove(xs[i].cube.yb);
                    current.remove(xs[i].cube.ye);
                }
                ++i;
            } while (i < xsn && xs[i].val == xs[i-1].val);

            currentArea = findArea(current);
        }
        return volume;
    }

    private long findArea(TreeSet<Point> points) {
        int n = points.size();
        if (n == 0) {
            return 0;
        }

        TreeSet<Point> current = new TreeSet<Point>();
        int area = 0;

        int lastY = 0;
        int currentLen = 0;

        for (Point curry : points) {
            if (curry.val != lastY) {
                currentLen = findLen(current);

                area += currentLen * (curry.val - lastY);
                lastY = curry.val;
            }
            if (curry.begin) {
                current.add(curry.cube.zb);
            } else {
                current.remove(curry.cube.zb);
            }
        }

        return area;
    }

    private int findLen(TreeSet<Point> points) {
        int n = points.size();
        if (n == 0) {
            return 0;
        }

        Iterator<Point> pIt = points.iterator();
        Point first = pIt.next();
        int lastEnd = first.cube.z2;
        int len = lastEnd - first.val;
        while (pIt.hasNext()) {
            Point curr = pIt.next();
            int z2 = curr.cube.z2;
            if (curr.val >= lastEnd) {
                lastEnd = z2;
                len += lastEnd - curr.val;
            } else if (z2 > lastEnd) {
                len += z2 - lastEnd;
                lastEnd = z2;
            }
        }

        return len;
    }

    public static void main(String[] args) {
        Tester.test(new Task_212());
    }
}