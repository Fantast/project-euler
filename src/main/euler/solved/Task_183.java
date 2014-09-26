package solved;

import tasks.ITask;
import tasks.Tester;

import static java.lang.Math.pow;
import static utils.Fraction.isTermniating;

//Answer : 48861552
public class Task_183 implements ITask {

    long MAX = 10000;

    public void solving() {
        System.out.println(isTermniating(1, 4));
        System.out.println(isTermniating(2, 8));
        System.out.println(isTermniating(1, 3));
        System.out.println(isTermniating(5, 6));

        long res = 0;
        for (long n = 5; n <= MAX; ++n) {
            double pm = n;
            long im = 1;
            for (long i = 2; i < n; ++i) {
                double km = i / (double) im;
                double kn = i / (double) n;
                double chk = pow(km, im) * pow(kn, i - im);

                if (chk < 1) {
                    im = i;
                }
            }

            res = res + (isTermniating(n, im) ? -n : n);
        }

        System.out.println(res);
    }

    public static void main(String[] args) {
        Tester.test(new Task_183());
    }
}
