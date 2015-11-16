package solved;

import tasks.AbstractTask;
import tasks.Tester;
import utils.log.Logger;

import java.math.BigInteger;

import static java.math.BigInteger.ONE;
import static utils.MyMath.totient;

//Answer : 50660591862310323
public class Task_512 extends AbstractTask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_512());
        Logger.close();
    }

//        int n = 100;
    int n = 5*100000000;

    BigInteger b2 = bi(2);

    long phi[] = new long[n+1];

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
        computePhi();

        long res = 0;
        for (int i = 1; i <= n; ++i) {
//            BigInteger phi = bi(totient(i));
//            BigInteger bi = bi(i);
//            BigInteger bi1 = bi(i-1);
//            BigInteger mod = bi(i+1);
//
//            BigInteger r;
//            if (i%2==0) {
//                BigInteger bi2 = bi(i/2);
//                r = ONE.add(bi.modPow(bi1, mod)).multiply(bi2).multiply(phi).mod(mod);
//            } else {
//                BigInteger mod2 = bi(2*i+2);
//                r = ONE.add(bi.modPow(bi1, mod2)).multiply(phi).mod(mod2).divide(b2);
//            }
//
//            if (i%2==1 || !r.equals(ZERO)) {
//                System.out.println(i + ": " + phi.equals(r) + " : " + r);
//            }
//
//            res += r.longValue();

            res += i%2==0 ? 0 : phi[i];
        }
        System.out.println(res);
    }

    private void computePhi() {
        for (int i = 1; i <= n; ++i) {
            phi[i] = i;
        }

        for (int i = 2; i <= n; ++i) {
            progress1000000(i);
            if (phi[i] == i) {
                for (int j = i; j <= n; j+=i) {
                    phi[j] = phi[j] - phi[j]/i;
                }
            }
        }
    }
}
