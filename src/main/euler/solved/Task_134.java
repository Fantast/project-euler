package solved;

import tasks.ITask;
import tasks.Tester;

import static java.lang.Math.log;
import static utils.MyMath.getPrimesBetween;

//Answer : 18613426663617118
//runs about 10 minutes... but whatever...
public class Task_134 implements ITask {
    long pow10[] = {
        1, 10, 100, 1000, 10000, 100000, 1000000, 10000000,
        100000000, 1000000000, 10000000000L, 100000000000L
    };

    public void solving() {
        long res = 0;
        
        long pr[] = getPrimesBetween(5, 1000005);
        for (int i = 0; i < pr.length - 1; ++i) {
            if (i % 10000==0) {
                System.out.println("Progress: " + i);
                System.out.println(res);
            }
            long p1 = pr[i];
            long p2 = pr[i + 1];

            int k = (int) ((log(p1) / log(10)) + 1);
            for (long a = 0; ;++a) {
                long s = a*pow10[k] + p1;
                if (s % p2 == 0) {
                    res += s;
                    break;
                }
            }
        }

        System.out.println(res);
    }

    public static void main(String[] args) {
        Tester.test(new Task_134());
    }
}
