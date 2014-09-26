package solved;

import tasks.ITask;
import tasks.Tester;
import utils.MyMath;
import utils.log.Logger;

import java.util.Set;
import java.util.TreeSet;

//Answer : 2209
public class Task_293 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_293());
        Logger.close();
    }

    long lim = 1000000000;
    long primes[] = new long[]{2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53};

    long sum = 0;
    Set<Long> all = new TreeSet<Long>();

    public void solving() {
        find(0, 2);

        System.out.println(sum);
    }

    private void find(int ind, long n) {
        if (n > lim) {
            return;
        }

        for (long m = 3; ; m += 2) {
            if (MyMath.isProbablePrime(n + m, 2) && MyMath.isProbablePrime(n + m, 15)) {
                if (!all.contains(m)) {
                    all.add(m);
                    sum += m;
                }
                break;
            }
        }

        find(ind, n * primes[ind]);
        find(ind + 1, n * primes[ind + 1]);
    }
}
