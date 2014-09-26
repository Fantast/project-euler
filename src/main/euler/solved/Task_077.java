package solved;

import tasks.ITask;

import static utils.MyMath.getPrimesBetween;
import static utils.OtherUtils.deepFillLong;

//Answer : 71
public class Task_077 implements ITask {
    long primes[] = getPrimesBetween(0, 1000);
    int n;
    public void solving() {
        for (n = 10; ; ++n) {
            deepFillLong(d, -1);
            if (get(0, 0) > 5000) {
                System.out.println(n);
                return;
            }
        }
    }

    long d[][] = new long[100][100];
    private long get(int beg, int sum) {
        if (sum == n) return 1;
        if (d[beg][sum] != -1) return d[beg][sum];

        long res = 0;
        for (int i = beg; primes[i] <= (n-sum); ++i) {
            res += get(i, (int) (sum+primes[i]));
        }

        return d[beg][sum] = res;
    }
}
