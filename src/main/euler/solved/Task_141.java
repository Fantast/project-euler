package solved;

import tasks.ITask;
import tasks.Tester;

import static java.lang.Math.sqrt;

//Answer : 878454337159
public class Task_141 implements ITask {

//    final long LIM = 100000L;
    final long LIM = 1000000000000L;
    private int PLIM = 200;

    public void solving() {
        long res = 0;
        for (long n = 2; n * n <= LIM; ++n) {
            long n2 = n * n;

            FRAC:
            for (long p = 2; p < PLIM; ++p) {
                long p3 = p*p*p;
                for (long q = 1; q < p; ++q) {
                    if ((4*n2)%q != 0) continue;
                    
                    long q2 = q*q;
                    long d = (q2 + (4*n2)/q*p3);

                    long dr = (long) sqrt(d);
                    if (dr*dr == d && (dr-q)%(2*p3)==0) {
                        long r = (dr-q)*q2/(2*p3);
                        System.out.println(n2 + ": {" + p + "/" + q + "}: " + r + ": " + r * p / q + ": " + r * p * p / q / q);
                        res += n2;
                        break FRAC;
                    }
                }
            }
        }

        System.out.println(res);
    }

    public static void main(String[] args) {
        Tester.test(new Task_141());
    }
}
