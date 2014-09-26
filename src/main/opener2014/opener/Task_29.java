package opener;

import tasks.ITask;
import tasks.Tester;
import utils.log.Logger;

import java.util.Arrays;

//Answer :
public class Task_29 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_29());
        Logger.close();
    }

    int a1 = 68;
    int a2 = 165;
    int b1 = 45;
    int b2 = 211;
    int c1 = 10;
    int c2 = 238;
    int d1 = 14;
    int d2 = 198;
    int e1 = 45;
    int e2 = 212;

    long a[] = new long[] {a1, a2};
    long b[] = new long[] {b1, b2};
    long c[] = new long[] {c1, c2};
    long d[] = new long[] {d1, d2};
    long e[] = new long[] {e1, e2};
//    long a[] = new long[] {1, 2};
//    long b[] = new long[] {1, 2};
//    long c[] = new long[] {1, 2};
//    long d[] = new long[] {1, 2};
//    long e[] = new long[] {1, 2};

    long totals[] = new long[244];
    public void solving() {
        long tot = 1;
        int size = 1;
        for (int i = 1; i <= 6; ++i) {
            totals[size] = tot;
            tot *= 243L - 2*5 - 1;
            size *= 3;
        }

//        long r = (a2-a1)*(b2-b1)*(c2-c1)*(d2-d1)*(e2-e1);
//        long r = (a[1]-a[0])*(b[1]-b[0])*(c[1]-c[0])*(d[1]-d[0])*(e[1]-e[0]);

        long res = combs(0, 5) - combs(1, 4) + combs(2, 3) - combs(3, 2) + combs(4, 1) - combs(5, 0);
//        System.out.println(r);
        System.out.println(res);
    }

    private long combs(int d1, int d2) {
        System.out.println(d1 + " " + d2);
        long combs = combs(d1, d2, 0);
        System.out.println(" = " + combs);
        return combs;
    }

    int sides[] = new int[5];
    private long combs(int d1, int d2, int ind) {
        if (ind == 5) {
//            System.out.println(Arrays.toString(sides));
            return get(a[sides[0]], b[sides[1]], c[sides[2]], d[sides[3]], e[sides[4]], 243);
        }
        long res = 0;
        if (d1 > 0) {
            sides[ind] = 0;
            res += combs(d1 - 1, d2, ind + 1);
        }
        if (d2 > 0) {
            sides[ind] = 1;
            res += combs(d1, d2 - 1, ind + 1);
        }
        return res;
    }

    public long get(long a0, long b0, long c0, long d0, long e0, int size) {
//        if (1 == 1) {
//            return a0*b0*c0*d0*e0;
//        }

        if (a0 >= size && b0 >= size && c0 >= size && d0 >= size && e0 >= size) {
            return totals[size];
        }

        long res = 0;
        int ns = size / 3;
        int cone = 0;
        long a = a0;
        for (int ai = 0; ai < 3; ai++, a -= ns) {
            if (a <= 0) break;
            if (ai == 1) cone++;
            long b = b0;
            for (int bi = 0; bi < 3; bi++, b -= ns) {
                if (b <= 0) break;
                if (bi == 1) cone++;
                long c = c0;
                for (int ci = 0; ci < 3; ci++, c -= ns) {
                    if (c <= 0) break;
                    if (ci == 1) cone++;
                    long d = d0;
                    for (int di = 0; di < 3; di++, d -= ns) {
                        if (d <= 0) break;
                        if (di == 1) {
                            if (cone == 3) continue;
                            cone++;
                        }
                        long e = e0;
                        for (int ei = 0; ei < 3; ei++, e -= ns) {
                            if (e <= 0) break;
                            if (ei == 1 && cone == 3) continue;
                            if (ns == 1) {
                                ++res;
                            } else {
                                res += get(a, b, c, d, e, ns);
                            }
                        }
                        if (di == 1) cone--;
                    }
                    if (ci == 1) cone--;
                }
                if (bi == 1) cone--;
            }
            if (ai == 1) cone--;
        }

        return res;
    }
}
