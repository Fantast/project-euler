package solved;

import tasks.ITask;
import tasks.Tester;

import static java.math.BigInteger.ONE;
import static java.math.BigInteger.valueOf;

//Answer : 5437849
public class Task_216 implements ITask {

    private long LIM = 50000000;

    public void solving() {
        long res = 0;
        for (long n = 1; n <= LIM; ++n) {
            if (n%1000000 == 0) {
                System.out.println("Progress: " + n);
            }
            if (isProbablePrime(n)) {
                ++res;
            }
        }
        System.out.println(res);
    }

    private boolean isProbablePrime(long n) {
        return valueOf(2*n).multiply(valueOf(n)).subtract(ONE).isProbablePrime(3);
    }

    public static void main(String[] args) {
        Tester.test(new Task_216());
    }
}
