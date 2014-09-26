package solved;

import tasks.ITask;
import tasks.Tester;
import utils.log.Logger;

import static utils.MyMath.gcd;
import static utils.MyMath.isPrime;

//Answer : 149253
public class Task_130 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_130());
        Logger.close();
    }

    public void solving() {
        long res = 0;
        int cnt = 0;
        for (long n = 2; ; ++n) {
            if (gcd(10, n) != 1) continue;
            if (isPrime(n)) continue;
            long a = A(n);
            if ((n-1)%a == 0 ) {
                System.out.println(n);
                res += n;
                if (++cnt == 25) break;
            }
        }

        System.out.println( "Sum: " + res );
    }

    private long A(long n) {
        long k = 1;
        long r = 1;

        while (r != 0) {
            r = (10*r+1) % n;
            ++k;
            if (k >= n) return n;
        }

        return k;
    }
}
