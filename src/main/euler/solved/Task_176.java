package solved;

import tasks.ITask;
import tasks.Tester;

import static utils.MyMath.*;

//Answer : 96818198400000
//  Sloane: http://www.research.att.com/~njas/sequences/A046079
//  Let n = (2^a0)*(p1^a1)*...*(pk^ak).
//  Then a(n) = [(2*a0 - 1)*(2*a1 + 1)*(2*a2 + 1)*(2*a3 + 1)*...*(2*ak + 1) - 1]/2.
//  Note that if there is no a0 term, i.e. if n is odd, then the first term is simply omitted
//  - Temple Keller (temple.keller(AT)gmail.com), Jan 05 2008
public class Task_176 implements ITask {
    private long NEED = 47547;

    public void solving() {
        System.out.println(getPrimeDivisors(NEED*2+1));
        // ==>
        long N = pow(2L, 10) * pow(3, 6) * pow(5, 5) * pow(7, 3) * pow(11, 2);

        System.out.println(N);
        System.out.println(C(N));
    }

    private long C(long n) {
        if (n % 2 == 0) {
            n /= 2;
        }
        long res = 1;
        for (long p : getCachedPrimesInternal()) {
            if (p * p > n) {
                // prime n remained, so c == 1 for prime n
                res *= 3;
                break;
            }

            long c = 0;
            while (n % p == 0) {
                n /= p;
                ++c;
            }
            res *= (2 * c + 1);

            if (n == 1) {
                break;
            }
        }
        return (res - 1) / 2;
    }

    public static void main(String[] args) {
        Tester.test(new Task_176());
    }
}
