package opener15;

import tasks.AbstractTask;
import tasks.Tester;
import utils.log.Logger;

import java.math.BigInteger;

//Answer :
public class Task_15 extends AbstractTask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_15());
        Logger.close();
    }

    public void solving() {
        BigInteger N = BigInteger.ONE;
        for (long i = 2; i <= 2015; ++i) {
            N = N.multiply(BigInteger.valueOf(i));
        }
        BigInteger n = N;
        BigInteger a = BigInteger.ONE;
        BigInteger b = BigInteger.ZERO;
        BigInteger two = BigInteger.valueOf(2L);
        while (n.compareTo(BigInteger.ZERO) != 0) {
            if (n.mod(two).compareTo(BigInteger.ZERO) == 0) {
                a = a.add(b);
                n = n.divide(two);
            } else {
                b = a.add(b);
                n = n.subtract(BigInteger.ONE).divide(two);
            }
        }
        System.out.println(b);
        System.out.println(a);
        System.out.println(n);
    }
}
