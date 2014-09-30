package solved;

import tasks.AbstractTask;
import tasks.Tester;
import utils.log.Logger;

import java.util.HashSet;
import java.util.Set;

//Answer : 3916160068885
public class Task_167 extends AbstractTask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_167());
        Logger.close();
    }

    long k = 100000000000L;
    //see: http://oeis.org/A100729
    long periods[] = new long[] {0, 0, 32, 26, 444, 1628, 5906, 80, 126960, 380882, 2097152};
    //see: http://oeis.org/A100730
    long diffs[] = new long[] {0, 0, 126, 126, 1778, 6510, 23622, 510, 507842, 1523526, 8388606};

    public void solving() {
        long res = 0;
        Set<Long> all = new HashSet<>();
        for (int n = 2; n <= 10; ++n) {
            long period = periods[n];
            long diff = diffs[n];

            long nonPeriodicCnt = n + 4 + 1;

            long even1 = 2;
            long even2 = 4*(n+1);

            long pcnt = (k - nonPeriodicCnt)/period;
            long ind = k - pcnt*period + 1;

            all.clear();

            for (long i = 2*n+1; i < even2; i += 2) {
                all.add(i);
            }

            long next = n + 4 + 1;
            long base = even2 + 1;
            for (long i = even2 + 1; next < ind; i += 2) {
                boolean can1 = all.contains(i - even1);
                boolean can2 = all.contains(i - even2);
                if ((can1 && !can2) || (!can1 && can2)) {
                    all.add(i);
                    next++;
                    if (next == ind) {
                        base = i;
                        break;
                    }
                }
            }

            long e = base + pcnt * diff;
            res += e;

            System.out.println(n + ": " + base + "+" + pcnt + "*" + diff + " = " + e);
        }
        System.out.println(res);
    }
}
