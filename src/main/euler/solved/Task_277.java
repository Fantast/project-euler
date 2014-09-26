package solved;

import tasks.ITask;
import tasks.Tester;
import utils.log.Logger;

import java.math.BigInteger;

//Answer : 1125977393124310
public class Task_277 implements ITask {

    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_277());
        Logger.close();
    }
    String istr = "UDDDUdddDDUDDddDdDddDDUDDdUUDd";
    long LIM = 1000000000000000L;

    public void solving() {
        long p3 = 1;
        long b = 0;
        long d = 1;
        for (char ch : new StringBuffer(istr).reverse().toString().toCharArray()) {
            switch (ch) {
                case 'D':
                    p3 *= 3;
                    b *= 3;
                    break;
                case 'U':
                    p3 *= 3;
                    b = 3 * b - 2 * d;
                    d *= 4;
                    break;
                case 'd':
                    p3 *= 3;
                    b = 3 * b + d;
                    d *= 2;
                    break;
            }
            System.out.println(p3 + " " + b + " " + d);
        }

        System.out.println(" GO!");


        BigInteger bp3 = BigInteger.valueOf(p3);
        BigInteger bb = BigInteger.valueOf(b);
        BigInteger bd = BigInteger.valueOf(d);
        BigInteger bLIM = BigInteger.valueOf(LIM);
        for (long k = LIM / p3 * d - b / p3;; ++k) {
            BigInteger n = BigInteger.valueOf(k).multiply(bp3).add(bb);
            BigInteger mr[] = n.divideAndRemainder(bd);
            if (mr[1].equals(BigInteger.ZERO) && mr[0].compareTo(bLIM) > 0) {
                System.out.println(mr[0]);
                break;
            }
        }
    }
}
