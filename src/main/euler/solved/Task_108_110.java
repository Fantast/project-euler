package solved;

import tasks.ITask;
import tasks.Tester;

import static utils.MyMath.getCachedPrimesInternal;

//Answer 108: 180180
//Answer 110: 9350130049860600
public class Task_108_110 implements ITask {
    public void solving() {
        long n = 2L*3L*5*7*11*13;
        while (C(n) <= 1000) {
            n += 2L*3L*5*7*11*13;
        }
        System.out.println("108: " + n);

        n = 2L*3L*5*7*11*13*17*19*23*29L;
        while (C(n) <= 4000000) {
            n += 2L*3L*5*7*11*13*17*19*23*29L;
        }
        System.out.println("110: " + n);
    }

    public static void main(String[] args) {
        Tester.test(new Task_108_110());
    }

    private long C(long n) {
        long res = 1;
        for (long p : getCachedPrimesInternal()) {
            if (p*p > n) {
                // prime n remained, so c == 1 for prime n
                res *= 3;
                break;
            }

            long c = 0;
            while (n % p == 0 ) {
                n /= p;
                ++c;
            }
            res *= (2*c+1);

            if (n == 1) break;
        }
        return (res+1)/2;
    }
}
