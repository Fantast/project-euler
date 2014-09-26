package solved;

import tasks.ITask;
import tasks.Tester;
import utils.log.Logger;

import static utils.MyMath.isPrime;

//Answer : 26241
public class Task_058 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_058());
        Logger.close();
    }

    public void solving() {
        int all = 1;
        int primes = 0;

        int i;
        for (i = 3; ; i += 2) {
            int c1 = (i-2)*(i-2) + (i-1);
            int c2 = c1 + (i-1);
            int c3 = c2 + (i-1);
            all += 4;

            if ( isPrime(c1) ) ++primes;
            if ( isPrime(c2) ) ++primes;
            if ( isPrime(c3) ) ++primes;

            if (primes*100/all < 10) break;
        }

        System.out.println(i);
    }
}
