package tasks;

import static java.lang.Math.ceil;
import static java.lang.Math.sqrt;
import static utils.MyMath.numberOfDigits;
import static utils.MyMath.pow10;

//Answer :
public class Task_255 implements ITask {
    long Nmin = 10000000000000L;
    long Nmax = 100000000000000L;

    public void solving() {
        System.out.println(heronSqIters(Nmin));
        double res = 0;
        for (long n = 10000; n < 100000; ++n) {
            System.out.println(n + ": " + heronSqIters(n) + " " + sqrt(n));
            res += heronSqIters(n);
        }

        System.out.println(res/90000.0);
    }

    private long heronSqIters(long n) {
        int d = numberOfDigits(n);
        long x1;
        if (d%2==0) {
            x1 = 7*pow10[(d-2)/2];
        } else {
            x1 = 2*pow10[(d-1)/2];
        }

        int iter = 0;
        long x0 = -1;
        while (x0 != x1) {
            ++iter;
            long t = (long) ( (x1 + ceil(n / (double) x1)) / 2.0);
            x0 = x1;
            x1 = t;
        }
        return iter;
    }

    public static void main(String[] args) {
        Tester.test(new Task_255());
    }
}
