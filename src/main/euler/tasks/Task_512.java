package tasks;

import utils.MyMath;
import utils.log.Logger;

import java.math.BigInteger;

import static java.math.BigInteger.ONE;
import static java.math.BigInteger.ZERO;
import static utils.MyMath.modInverse;
import static utils.MyMath.modInverse2;
import static utils.MyMath.totient;

//Answer :
public class Task_512 extends AbstractTask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_512());
        Logger.close();
    }

        int n = 100;
//    int n = 5*100000000;

    BigInteger bn = bi(n);
    BigInteger b2 = bi(2);

    public void solving2() {
        f0(1);
//        f(2);
//        f(3);
//        f(4);
//        f(5);
//        f(6);
        f(7);
        f(8);
        f(9);
        f(10);
        f(11);
    }

    private void f0(long n) {
        long res = 0;
        long np = 1;
        for (long i = 1; i <= n; ++i) {
            np *= n;
            long tot = totient(np);
            res += tot;
        }
        res = res % (n+1);
        System.out.println(n + ": " + res);
    }

    private void f(long i) {
        BigInteger phi = bi(totient(i));
        BigInteger bi = bi(i);
        BigInteger bi1 = bi(i-1);
        BigInteger mod = bi(i+1);

        BigInteger r;
        if (i%2==0) {
            BigInteger bi2 = bi(i/2);
            r = ONE.add(bi.modPow(bi1, mod)).multiply(bi2).multiply(phi).mod(mod);
        } else {
            BigInteger mod2 = bi(2*i+2);
            r = ONE.add(bi.modPow(bi1, mod2)).multiply(phi).mod(mod2).divide(b2);
        }


        System.out.println(i + ": " + r);
    }

    public void solving() {
        long res = 0;
        long sum = 0;
        for (int i = 1; i <= n; ++i) {
//            progress100000(i);

            BigInteger phi = bi(totient(i));
            BigInteger bi = bi(i);
            BigInteger bi1 = bi(i-1);
            BigInteger mod = bi(i+1);

            BigInteger r;
            if (i%2==0) {
                BigInteger bi2 = bi(i/2);
                r = ONE.add(bi.modPow(bi1, mod)).multiply(bi2).multiply(phi).mod(mod);
            } else {
                BigInteger mod2 = bi(2*i+2);
                r = ONE.add(bi.modPow(bi1, mod2)).multiply(phi).mod(mod2).divide(b2);
            }

            if (i%2==1 || !r.equals(ZERO)) {
                System.out.println(i + ": " + phi.equals(r) + " : " + r);
            }

//            sum += r.longValue();
            sum += i%2==0 ? 0 : totient(i);
        }
        System.out.println(sum);
    }
}
