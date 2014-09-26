package solved;

import tasks.ITask;

import java.util.Arrays;

import static utils.MyMath.getPrimesBetween;

//Answer : 2969 6299 9629
public class Task_049 implements ITask {
    public void solving() {
        long res = 0;
        long p[] = getPrimesBetween(1000, 10000);

        int n = p.length;
        for (int i = 0; i < n; ++i) {
            for (int j = i+1; j < n; ++j) {
                if ( same(p[i], p[j]) ) {
                    long p3 = p[j] + p[j] - p[i];
                    if (p3 > 10000) break;
                    if (same(p3, p[i]) && Arrays.binarySearch(p, p3)>=0 ) {
                        System.out.println(p[i] + " " + p[j] + " " + p3);
                    }
                }
            }
        }
    }

    private boolean same(long a, long b) {
        long d1[] = new long[5];
        long d2[] = new long[5];

        for (int i = 0; i < 5; ++i) {
            d1[i] = a%10;
            d2[i] = b%10;

            a /= 10;
            b /= 10;
        }

        Arrays.sort(d1);
        Arrays.sort(d2);

        return Arrays.equals(d1, d2);
    }
}
