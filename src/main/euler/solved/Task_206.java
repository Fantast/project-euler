package solved;

import tasks.ITask;
import tasks.Tester;
import utils.log.Logger;

import static java.lang.Math.sqrt;

//Answer : 1389019170
public class Task_206 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_206());
        Logger.close();
    }

    public void solving() {
        long res = 0;

        long mn = 1020304050607080900L;
        long mx = 1929394959697989990L;

        long mxa = (long) sqrt(mx);
        long mna = (long) sqrt(mn);
        mna = (mna / 10) * 10;

        for (long i = mna; i <= mxa; i+=10 ) {
            long s = i * i;
            if (check(s)) {
                res = i;
                break;
            }
        }
        System.out.println("Result: " + res);
    }

    private boolean check(long n) {
        for (int i = 9; i >= 1; --i) {
            n /= 100;
            int d = (int) (n % 10);
            if (d != i) return false;
        }

        return true;
    }
}