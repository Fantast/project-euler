package solved;

import tasks.ITask;
import tasks.Tester;
import utils.MyMath;
import utils.log.Logger;

import java.math.BigInteger;

import static java.math.BigInteger.*;
import static utils.MyMath.*;

//Answer : 4617456485273129588
//Afterwards thoughts: there is a simple approach.. for primes other then 3k+1 - there are only solutions in form - GCD*k+1
//so it's possible to just iterate over all such numbers and check that reminders on other primes is any of possible three...
public class Task_271 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_271());
        Logger.close();
    }

    long _primes[] = new long[] {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43};
//    long _primes[] = new long[] {7, 13};
    int pcnt = _primes.length;

    BigInteger primes[];

    int rcnt[] = new int[pcnt];
    BigInteger roots[][] = new BigInteger[pcnt][3];

    BigInteger res = ONE.negate();

    public void solving() {
        // see: http://oeis.org/A060839 for some thoughts
        //
        // x^3 = 1 mod n
        //
        // n = 13082761331670030 = 2*3*5*7*11*13*17*19*23*29*31*37*41*43
        //
        // by http://en.wikipedia.org/wiki/Fermat%27s_little_theorem
        //
        // x^3 = 1 (mod n) when
        //
        // x^3 = 1 (mod pi), for all pi = {2..43}
        //
        // to find x^3 = 1 (mod pi)
        // (x-1)(x^2+x+1) = 0 mod pi
        //  1) x = 1
        //  2) (2*x+1)^2 = -3 (mod pi)
        // Then to solve a^2 = n (mod pi) use Shanks-Tonelli algorithm.

        MyMath.setMaxPrimesToCache(100);

        primes = new BigInteger[pcnt];
        for (int i = 0; i < _primes.length; i++) {
            primes[i] = valueOf(_primes[i]);
        }

        for (int i = 0; i < pcnt; ++i) {
            long p = _primes[i];

            roots[i][0] = ONE;
            if (p % 3 != 1) {
                rcnt[i] = 1;
            } else {
                rcnt[i] = 3;

                long x1 = modSqrt(p - 3, p);
                long x2 = p - x1;
                if (x1 % 2 == 0) {
                    x1 += p;
                }
                if (x2 % 2 == 0) {
                    x2 += p;
                }

                x1 = (x1-1) / 2;
                x2 = (x2-1) / 2;

                roots[i][1] = valueOf(x1);
                roots[i][2] = valueOf(x2);
            }
        }

        find(0, ZERO, ONE);
        System.out.println(res);
    }

    private void find(int ind, BigInteger n, BigInteger g) {
        if (ind == pcnt) {
            n = n.mod(g).add(g).mod(g);
            res = res.add(n);
            System.out.println(n);
            return;
        }

        BigInteger p = primes[ind];
        BigInteger[] rs = roots[ind];
        for (int i = 0; i < rcnt[ind]; ++i) {
            BigInteger s[] = bSolveLinearDiaophanteEq(p, g.negate(), n.subtract(rs[i]));
            find(ind + 1, p.multiply(s[0]).add(rs[i]), g.multiply(p));
        }
    }
}
