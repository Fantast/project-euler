package solved;

import tasks.ITask;

import static utils.MyMath.gcd;
import static utils.MyMath.isPowerOfTwo;

//Answer : 44043947822
public class Task_207 implements ITask {

    public void solving() {
        long k = 1;
        long parts = 0;
        long perfs = 0;

        long d = 1;
        long n = 1;

        long jk = 0;
        while (n*12345 >= d) {
            k += 2;
            long c = (long) ((1 + k) / 2);
            parts++;
            if (isPowerOfTwo(c)) {
                perfs++;
            }

            long g = gcd(perfs, parts);
            n = perfs / g;
            d = parts / g;

            jk = (k*k - 1)/4;
//            System.out.println(jk + ": " + n + "/" + d);
        }
        System.out.println(jk);
    }
}
