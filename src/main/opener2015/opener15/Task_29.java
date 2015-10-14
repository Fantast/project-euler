package opener15;

import tasks.AbstractTask;
import tasks.Tester;
import utils.log.Logger;

import java.math.BigInteger;

import static java.math.BigInteger.valueOf;

//Answer :
public class Task_29 extends AbstractTask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_29());
        Logger.close();
    }

    public void solving() {
//        System.out.println(Math.abs(-Integer.MAX_VALUE));
        System.out.println(x(Integer.MAX_VALUE));
//        System.out.println(x(Integer.MIN_VALUE));

//        BigInteger bi;
//        BigInteger sum = BigInteger.ZERO;
//        for (int i = 1; i < Integer.MAX_VALUE; ++i) {
//            progress10000000(i);
//            if (x(i) == null) {
//                bi = valueOf(i);
//                sum = sum.add(bi).add(bi);
//            }
//        }
//        System.out.println(sum);

        if (x(Integer.MAX_VALUE) == null) {

        }

        System.out.println(
                new BigInteger("1346581031431841710")
                        .add(valueOf(Integer.MAX_VALUE).abs()).add(valueOf(Integer.MAX_VALUE).abs())
//                        .add(valueOf(Integer.MIN_VALUE).abs()).add(valueOf(Integer.MIN_VALUE).abs())
        );
    }

    private Integer x(int x) {
//        x = Math.abs(x);

        if (x == 0) {
            return 0;
        }
        int y = 1;
        int z = 0;
        do {
            z = y;
            y += x / y;
            y >>= 1;
            z -= y;
//            System.out.println(z);
            if (z == -1 || z == 1) {
                return null;
            }
        } while (z != 0);
        return y;
    }
}
