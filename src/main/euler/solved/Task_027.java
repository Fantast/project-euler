package solved;

import tasks.ITask;

import static utils.MyMath.doEratosthen;
import static utils.MyMath.isPrime;

//Answer : -59231
public class Task_027 implements ITask {
    public void solving() {
        long res = 0;

        long primes[] = new long[1000];
        int pc = doEratosthen(primes, 1000);

        long maxn = 0;
        for (int ib = 0; ib < pc; ++ib) {
            long b = primes[ib];

            for (long a = -999; a < 1000; a += 2) {
                long n;
                for (n = 0; ; ++n) {
                    long m = n*n + a*n + b;
                    if (m < 0) break;
                    if ( !isPrime(m) ) break;
                }

                if (n > maxn) {
                    maxn = n;
                    res = a*b;
                    System.out.println("n^2 + " + a + "n + " + b + " : maxn = " + maxn);
                }
            }
        }

        System.out.println(res);
    }
}
