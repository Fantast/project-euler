package tasks;

import utils.MyMath;
import utils.log.Logger;
import utils.structures.RedBlackBST;

import static utils.structures.RedBlackBST.Processor;

//Answer :
public class Task_464 extends AbstractTask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_464());
        Logger.close();
    }
    
//    public int LIM = 20000000;
    public int LIM = 20000;

    int n;
    int factorCnt;
    
    int meb[] = new int[LIM + 1];
    int pos[] = new int[LIM + 1];
    int neg[] = new int[LIM + 1];
    int f1[] = new int[LIM + 1];
    int f2[] = new int[LIM + 1];
    Pair m1[] = new Pair[LIM + 1];
    Pair m2[] = new Pair[LIM + 1];
    Pair mk1[] = new Pair[LIM + 1];
    Pair mk2[] = new Pair[LIM + 1];

    final int outres[] = new int[1];
    final int currn[] = new int[1];
    public void solving() {
//        99*(n(b) - n(a-1)) <= 100*(p(b) - p(a-1))
//        99*(p(b) - p(a-1)) <= 100*(n(b) - n(a-1))
//
//        a <= b
//        99*n(b) - 100*p(b) <= 99*n(a-1) - 100*p(a-1)
//        99*p(b) - 100*n(b) <= 99*p(a-1) - 100*n(a-1)

        MyMath.setMaxPrimesToCache(200000);
        long[] primes = MyMath.getCachedPrimes();

        System.out.println("Go");
        m1[0] = new Pair(0, 0);
        m2[0] = new Pair(0, 0);
        for (n = 1; n <= LIM; ++n) {
            progress100000(n);
            factorCnt = 0;
            long n2 = n;
            
            for (long p : primes) {
                if (n2 % p == 0) {
                    n2 /= p;
                    if (n2 % p == 0) {
                        factorCnt = -1;
                        break;
                    }
                    factorCnt++;
                }

                if (p * p >= n2) {
                    if (n2 != 1) {
                        factorCnt++;
                    }
                    break;
                }
            }

            meb[n] = factorCnt < 0
                     ? 0
                     : factorCnt % 2 == 0
                       ? 1
                       : -1;

            if (meb[n] > 0) {
                pos[n] = pos[n-1] + 1;
            } else {
                pos[n] = pos[n-1];
            }
            if (meb[n] < 0) {
                neg[n] = neg[n-1] + 1;
            } else {
                neg[n] = neg[n-1];
            }
            f1[n] = 99*neg[n] - 100*pos[n];
            f2[n] = 99*pos[n] - 100*neg[n];
            m1[n] = new Pair(n, f1[n]);
            m2[n] = new Pair(n, f2[n]);
            
            mk1[n] = new Pair(n, f1[n] - 1);
            mk2[n] = new Pair(n, f2[n] - 1);
        }

        timeStamp();
        bruteForce();
        timeStamp();

        System.out.println("-----------------");
        Processor<Pair, Pair> processor1 = new Processor<Pair, Pair>() {
            @Override
            public void process(Pair k, Pair v) {
                if (f2[k.n] >= f2[currn[0]]) {
                    ++outres[0];
//                        System.out.println(n + " " + (e.n+1));
                }
            }
        };
        Processor<Pair, Pair> processor2 = new Processor<Pair, Pair>() {
            @Override
            public void process(Pair k, Pair v) {
                if (f1[k.n] >= f1[currn[0]]) {
                    ++outres[0];
//                        System.out.println(n + " " + (e.n+1));
                }
            }
        };

        RedBlackBST<Pair, Pair> t1 = new RedBlackBST<>();
        RedBlackBST<Pair, Pair> t2 = new RedBlackBST<>();
        long res = 0;
        for (int n = 1; n <= LIM; ++n) {
            progress10000(n);
            if (meb[n] != 0) {
                if (f1[n] <= f1[n - 1] && f2[n] <= f2[n - 1]) {
                    //a == b
                    //                System.out.println(n + " " + n);
                    res++;
                }

                //            SortedSet<Pair> h1 = t1.tailSet(m1[n]);
                //            SortedSet<Pair> h2 = t2.tailSet(m2[n]);
                Pair k1 = mk1[n];
                Pair k2 = mk2[n];
                int r1 = t1.rank(k1);
                int r2 = t2.rank(k2);

                currn[0] = n;
                outres[0] = 0;
                if (r1 > r2) {
                    t1.tailProcess(k1, processor1);
                } else {
                    t2.tailProcess(k2, processor2);
                }
            } else {
                outres[0]++;
            }
            res += outres[0];
            t1.put(m1[n - 1], m1[n - 1]);
            t2.put(m2[n - 1], m1[n - 1]);
        }
        timeStamp();

        System.out.println(res);

    }

    private void bruteForce() {
        long res = 0;
        for (int b = 1; b <= LIM; ++b) {
            for (int a = 1; a <= b; ++a) {
                int pab = P(a, b);
                int nab = N(a, b);
                if (99*nab <= 100*pab && 99*pab <= 100*nab) {
                    ++ res;
//                    System.out.println(b + " " + a + ": " + (f1[b] <= f1[a-1]) + " " + (f2[b] <= f2[a-1]));
                }
            }
        }
        System.out.println("Brute-forced: " + res);
    }

    private int P(int a, int b) {
        return pos[b] - pos[a-1];
    }

    private int N(int a, int b) {
        return neg[b] - neg[a-1];
    }

    static class Pair implements Comparable<Pair> {
        int n;
        int f;

        Pair(int n, int f) {
            this.n = n;
            this.f = f;
        }

        @Override
        public int hashCode() {
            int result = n;
            result = 31 * result + f;
            return result;
        }

        @Override
        public int compareTo(Pair o) {
            int c = Integer.compare(f, o.f);
            return c != 0 ? c : Integer.compare(n, o.n);
        }

        public String toString() {
            return n + "->" + f;
        }
    }

}
