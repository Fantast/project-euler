package solved;

import tasks.ITask;
import tasks.Tester;
import utils.log.Logger;

import static utils.MyMath.getPrimesBetween;

//Answer : 843296
public class Task_132 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_132());
        Logger.close();
    }

    private static final long REP_UNIT = 1000000000L ;

    public void solving() {
        long res = 0;
        long prs[] = getPrimesBetween(0, 1000000);

        int cnt = 0;
        for (int i = 0; ; ++i) {
            if (isRepUnitDivisibleOn(REP_UNIT, (int) prs[i])) {
                System.out.println(prs[i]);
                res += prs[i];
                if (++cnt == 40) break;
            }
        }

        System.out.println("Sum: " + res );
    }

    private boolean isRepUnitDivisibleOn(long repUnit, int n) {
        int r = 1;
        for (int i = 2; i <= n; ++i) {
            r = (10*r+1) % n;
            if (r == 0) {
                return (repUnit % i) == 0;
            }
        }

        return false;
    }
}
