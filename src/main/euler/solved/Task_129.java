package solved;

import tasks.ITask;
import tasks.Tester;
import utils.log.Logger;

import static utils.MyMath.gcd;

//Answer : 1000023
public class Task_129 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_129());
        Logger.close();
    }

    final long NEED_A = 1000000;

    public void solving() {
        for (long n = NEED_A; ; ++n) {
            if (gcd(10, n) != 1) continue;
            long a = A(n);
            if (a > NEED_A) {
                System.out.println( n );
                break;
            }
        }
    }

    private long A(long n) {
        long k = 1;
        long r = 1;

        while (r != 0) {
            r = (10*r+1) % n;
            ++k;
            if (k > NEED_A) return k;
        }

        return k;
    }
}
