package solved;

import tasks.ITask;
import tasks.Tester;
import utils.log.Logger;

import static java.lang.Math.min;

//Answer : 479742450
public class Task_154 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_154());
        Logger.close();
    }

    int n = 200000;
    long t2[] = new long[n+1];
    long t5[] = new long[n+1];

    public void solving() {
        for (int i = 1; i <= n; ++i) {
            t2[i] = t2[i-1];
            t5[i] = t5[i-1];

            int k = i;
            while (k % 5 == 0) {
                k /= 5;
                t5[i]++;
            }
            while (k % 2 == 0) {
                k /= 2;
                t2[i]++;
            }
        }

        long n5 = t5[n];
        long n2 = t2[n];
        long res = 0;
        for (int i = n; i > n/3; --i) {
            long i2 = n2 - t2[i];
            long i5 = n5 - t5[i];
            for (int j = min(n - i, i); j >= n-i-j && n-i-j >= 0; --j) {
                int k = n - i - j;
                long t10 = Math.min(i2 - t2[j] - t2[k], i5 - t5[j] - t5[k]);

                if (t10 > 11) {
                    if (i != j && i != k && j != k) {
                        res += 6;
                    } else {
                        res += 3;
                    }
                }
            }
        }
        System.out.println(res);
    }
}
