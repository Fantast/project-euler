package solved;

import tasks.ITask;
import tasks.Tester;

import static java.lang.Math.abs;
import static utils.MyMath.isPrime;

//Answer : 14516824220
public class Task_128 implements ITask {
    public void solving() {
        int cnt = 2;

        long spred = 1;
        long scurr = 2;
        long snext = 3;
        long predstart = 2;
        long currstart = 8;
        long nextstart = 20;
        long n = 8;
        for (; cnt < 2000; n++) {
            if (n == nextstart) {
                predstart += 6*spred;
                currstart += 6*scurr;
                nextstart += 6*snext;

                ++spred;
                ++scurr;
                ++snext;
            }

            long ni = n - currstart;

            long gi = ni/scurr;
            long ei = ni - scurr*gi;

            int c = 0;
            if (ei == 0) {
                //ok, now I know, we only need to check first corner...
                if (gi != 0) continue;
                //corner element
                c += check(n, number(predstart, spred, gi, ei));
                long nd = number(nextstart, snext, gi, ei);
                c += check(n, nd);
                c += check(n, nd+1);
                if (gi != 0) {
                    c += check(n, nd-1);
                } else {
                    c += check(n, nd + 6*snext-1);
                }
                if (c > 3) continue;
                if (gi == 0) {
                    c += check(n, nextstart-1);
                    
                }
            } else if (n==nextstart-1) {
                //end element.. other side elements not needed to check..
                long nd = number(nextstart, snext, gi, ei);
                c += check(n, nd);
                c += check(n, nd+1);

                nd = number(predstart, spred, gi, ei-1);
                c += check(n, nd);
                if (nd+1 != currstart) {
                    c += check(n, nd+1);
                } else {
                    c += check(n, predstart);
                    c += check(n, currstart);
                }
            }

            if (c == 3) {
                ++cnt;
                if (cnt % 100 == 0) {
                    System.out.println("Progress: " + cnt + " - " + n);
                }
            }
        }

        System.out.println(n - 1);
    }

    private long number(long start, long s, long gi, long ei) {
        return start + s*gi + ei;
    }

    private int check(long n, long nd) {
        return isPrime(abs(n - nd)) ? 1 : 0;
    }

    public static void main(String[] args) {
        Tester.test(new Task_128());
    }
}