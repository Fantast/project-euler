package solved;

import tasks.ITask;

import java.math.BigInteger;

import static utils.MyMath.getCachedPrimes;

//Answer : 21035
public class Task_123 implements ITask {
    public void solving() {
        long primes[] = getCachedPrimes();

        long limit = BigInteger.TEN.pow( 10 ).longValue();

        int i;
        for (i = 1; primes[i] < 1000000; ++i) {
            long p = primes[i-1];
            BigInteger ps = BigInteger.valueOf(p*p);
            BigInteger I = BigInteger.valueOf(i);

            BigInteger p1 = BigInteger.valueOf( p-1 );
            BigInteger p2 = BigInteger.valueOf( p+1 );
            long r = p1.modPow(I, ps).add( p2.modPow(I, ps) ).remainder(ps).longValue();
            if (r > limit) {
                System.out.println("Prime: " + p);
                System.out.println("Reminder: " + r);
                break;
            }
        }

        System.out.println("Index: " + i);
    }
}
