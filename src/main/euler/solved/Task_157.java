package solved;

import tasks.ITask;
import tasks.Tester;
import utils.log.Logger;

import static utils.MyMath.getDivisors;

//Answer : 53490
//            a+b    p
//            --- = --
//             ab   10n
//
//             2^n*5^n*(a+b) = p*a*b;
//
//             if (gcd(a,b) != 1) then
//                  2n*5n*(ag+bg) = p*a*b*g2 => 2n*5n*(a+b) = p*g2*(a+b)
//             => we need co-prime a, b... and we can get more solutions for every divisor of p
public class Task_157 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_157());
        Logger.close();
    }

    private int N = 9;
    private long p2[] = new long[N+1];
    private long p5[] = new long[N+1];
    public void solving() {
        p2[0] = p5[0] = 1;
        for (int i = 1; i <= N; ++i) {
            p2[i] = 2 * p2[i-1];
            p5[i] = 5 * p5[i-1];
        }

        long res = 0;
        for (int n = 1; n <= N; ++n) {
            for (int k2 = 0; k2 <= n; ++k2) {
                for (int k5 = 0; k5 <= n; ++k5) {
                    long p = (p2[k2] + p5[k5]) * p2[n - k2] * p5[n - k5];
                    res += getDivisors(p).size();

                    if (k2 != 0 && k5 != 0) {
                        p = (p2[k2]*p5[k5] + 1) * p2[n - k2] * p5[n - k5];
                        res += getDivisors(p).size();
                    }
                }
            }
        }
        System.out.println(res);
    }
}
