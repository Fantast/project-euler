package tasks;

import utils.MyMath;
import utils.log.Logger;

import java.math.BigInteger;

import static java.math.BigInteger.*;

//Answer :
public class Task_358 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_358());
        Logger.close();
    }

    public void solving() {
        long primes[] = MyMath.getPrimesBetween(0, 100000);

        for (long p : primes) {
            BigInteger bp = valueOf(p);
            BigInteger c = TEN.pow((int) p).subtract(ONE).divide(bp);

            String sc = c.toString();
            if (sc.startsWith("137") || sc.contains("00000000")) {
                Logger.out.println(p + ": " + c);
            }
        }
    }
}
