package opener;

import tasks.ITask;
import tasks.Tester;
import utils.MyMath;
import utils.log.Logger;

import java.math.BigInteger;

//Answer :
public class Task_6 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_6());
        Logger.close();
    }

    public void solving() {
        BigInteger e = new BigInteger("opener", 31);
        BigInteger m = new BigInteger("opener", 32);
//        BigInteger enc = new BigInteger("2014", 10);
        System.out.println(e);
        System.out.println(m);


        long enc = 2014;
        long E = e.longValue();
        long M = m.longValue();
        long m1 = 24917;
        long m2 = 33391;

        //e^-1 mod phi

        long phi = (m1-1)*(m2-1);
        System.out.println("phi: " + phi);

        long d = MyMath.modInverse(E, phi);
        System.out.println("d:" + d);
        System.out.println(E*d % phi);

        //c^d % m

        long res = BigInteger.valueOf(2014).modPow(BigInteger.valueOf(d), m).longValue();
        System.out.println(res);
        System.out.println(rsa(e, m, res));
    }

    private long rsa(BigInteger e, BigInteger m, long n) {
        return BigInteger.valueOf(n).modPow(e, m).longValue();
    }
}
