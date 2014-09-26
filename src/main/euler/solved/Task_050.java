package solved;

import tasks.ITask;

import static utils.MyMath.getCachedPrimes;
import static utils.MyMath.isPrime;

//Answer : 997651
public class Task_050 implements ITask {
    public void solving() {
        long[] primes = getCachedPrimes();
        int n = primes.length;

        long sum[] = new long[n+1];
        sum[0] = 0;
        for (int i = 0; i < n; ++i) {
            sum[i+1] = sum[i] + primes[i];
        }

        long res = 0;
        int cnt = 1;
        for (int i = 0; i < n-1; ++i) {
            for (int j = i+cnt; j < n; ++j) {
                long r = sum[j+1] - sum[i];
                if (r > 1000000) break;
                if (isPrime(r)) {
                    res = r;
                    cnt = j-i;
//                    System.out.println(r);
                }

            }
        }

        System.out.println(res);
    }
}
