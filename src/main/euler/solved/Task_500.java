package solved;

import tasks.AbstractTask;
import tasks.Tester;
import utils.MyMath;
import utils.log.Logger;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

//Answer : 35407281
public class Task_500 extends AbstractTask {

    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_500());
        Logger.close();
    }

    int n = 500500;
    BigInteger MOD = bi(500500507);

    TreeMap<BigInteger, PrimePower> priority = new TreeMap<>();

    public void solving() {
        long[] primes = MyMath.primes(n);

        for (int i = 0; i < n; ++i) {
            long prime = primes[i];
            priority.put(bi(prime), new PrimePower(prime));
        }

        BigInteger res = BigInteger.ONE;

        for (int i = 0; i < n; ++i) {
            Iterator<Map.Entry<BigInteger, PrimePower>> iterator = priority.entrySet().iterator();
            Map.Entry<BigInteger, PrimePower> e = iterator.next();
            iterator.remove();

            PrimePower pp = e.getValue();
            BigInteger val = e.getKey();

            res = res.multiply(val).mod(MOD);

            pp.next();
            priority.put(pp.value(), pp);
        }

        System.out.println(res);
    }

    static class PrimePower {
        long prime;
        int pow2;

        public PrimePower(long prime) {
            this.prime = prime;
            this.pow2 = 1;
        }

        public void next() {
            ++pow2;
        }

        public BigInteger value() {
            return bi(prime).pow(1 << (pow2 - 1));
        }
    }
}
