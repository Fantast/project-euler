package opener15;

import tasks.AbstractTask;
import tasks.Tester;
import utils.log.Logger;

import java.math.BigInteger;

import static java.math.BigInteger.valueOf;

//Answer : 66344502871348
public class Task_29 extends AbstractTask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_29());
        Logger.close();
    }

    public void solving() {
//        System.out.println(x(Integer.MAX_VALUE));
//        System.out.println(x(9878448));

        BigInteger sum = BigInteger.ZERO;

        BigInteger bi;
        for (int i = 1; i < Integer.MAX_VALUE; ++i) {
            progress10000000(i);
            if (x(i) == null) {
//                System.out.println(i);
                bi = valueOf(i);
                sum = sum.add(bi).add(bi);
            }
        }

        sum = sum.add(valueOf(Integer.MIN_VALUE).abs());
        System.out.println(sum);
    }

    private Integer x(int x) {
//        x = Math.abs(x);

        if (x == 0) {
            return 0;
        }
        int y = 1;
        int z = 0;
        int zprev = 0;
        do {
            zprev = z;
            z = y;
            y += x / y;
            y >>= 1;
            z -= y;
//            System.out.println(z);
            if (z == -1 && zprev == 1) {
                return null;
            }
        } while (z != 0);
        return y;
    }
}
