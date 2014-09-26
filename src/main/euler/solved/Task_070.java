package solved;

import tasks.ITask;

import java.util.Arrays;

import static java.lang.Math.log10;
import static utils.MyMath.phi;

//Answer : 8319823
public class Task_070 implements ITask {
    private static final int MAX = 10000000;
    public void solving() {
        long res = 0;

        double val = 100;
        for (int i = 2; i <= MAX; ++i) {
            long f = phi(i);
            double v = i / (double)f;
            if (v < val && same(i, f) ) {
//                System.out.println(i+" " +f);
                val = v;
                res = i;
            }
        }

        System.out.println( res );
    }

    private boolean same(long a, long b) {
        long d1[] = new long[10];
        long d2[] = new long[10];

        if ((int)log10(a) != (int)log10(b)) return false;

        int i = 0;
        while (a != 0) {
            d1[(int) (a%10)]++;
            d2[(int) (b%10)]++;
            a /= 10;
            b /= 10;
        }

        return Arrays.equals(d1, d2);
    }
}
