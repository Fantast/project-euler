package solved;

import tasks.ITask;
import tasks.Tester;
import utils.log.Logger;

import static utils.MyMath.isPrime;

//Answer : 1739023853137
public class Task_357 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_357());
        Logger.close();
    }

    private final long LIM = 100000000;

    public void solving() {
        long res = 1;
        TOP: for (long n = 2; n <= LIM; ++n) {
            for (long i = 1; i*i <= n; ++i) {
                if (n%i == 0) {
                    long pt = i + n / i;
                    if (!isPrime(pt)) {
                        continue TOP;
                    }
                }
            }
            System.out.println(" !!! FOUND !!! " + n);
            res += n;
        }

        System.out.println(res);
    }
}
