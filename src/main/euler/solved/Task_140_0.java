package solved;

import tasks.ITask;
import tasks.Tester;

import java.math.BigInteger;

import static java.lang.Math.sqrt;
import static java.math.BigInteger.valueOf;
import static utils.MyMath.isExactSquare;

//Answer : 5673835352990
public class Task_140_0 implements ITask {
    public void solving() {
        long res = 0;
        int cnt = 0;
        long n = 1;

//        long res = 2570836632L;
//        int cnt = 22;
//        long n = 1448582690L * 18L/10L;

        for (; cnt < 21; ++n) {
            if (isExactSquare(5*n*n + 14*n + 1)) {
                long d = (long) sqrt(5*n*n + 14*n + 1);

                cnt++;
                System.out.println(cnt + ": " + d + ": " + n);
                res += n;
            }
        }
        System.out.println(res);

        //continueing with BigIntegers...
        for (; cnt < 30; ++n) {
            BigInteger d = valueOf(5*n).multiply(valueOf(n)).add(valueOf(14*n + 1));
            if (isExactSquare(d)) {
//                long d = (long) sqrt(5*n*n + 14*n + 1);

                cnt++;
                System.out.println(cnt + ": " + n);
                res += n;
            }
        }

        System.out.println(res);
    }

    public static void main(String[] args) {
        Tester.test(new Task_140_0());
    }
}