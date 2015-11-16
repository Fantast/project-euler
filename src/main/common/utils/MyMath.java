package utils;

import org.apfloat.Apint;
import org.apfloat.ApintMath;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.*;

import static java.lang.Math.*;
import static java.math.BigInteger.*;

public class MyMath {
    public static long pow10[] = {
        1,
        10,
        100,
        1000,
        10000,
        100000,
        1000000,
        10000000,
        100000000,
        1000000000,
        10000000000L,
        100000000000L,
        1000000000000L,
        10000000000000L,
        1000000000000000L,
        10000000000000000L,
        100000000000000000L,
        1000000000000000000L
    };

    //see https://graphics.stanford.edu/~seander/bithacks.html#IntegerLogLookup
    private static int LogTable256[] = new int[]{
            -1, 0,
            1, 1,
            2, 2, 2, 2,
            3, 3, 3, 3, 3, 3, 3, 3,
            4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4,
            5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5,
            6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6,
            6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6,
            7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7,
            7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7,
            7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7,
            7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7
    };

    private static ArrayList<Long> cachedPrimes;
    private static long lastCachedPrime;

    public static BigInteger gcd(BigInteger a, BigInteger b) {
        return a.gcd(b);
    }

    public static long gcd(long a, long b) {
        if (b == 0) return a;
//        a = abs(a);
//        b = abs(b);

        while (b != 0) {
            long t = a % b;
            a = b;
            b = t;
        }
        return a;
    }

    public static int gcd(int a, int b) {
        if (b == 0) return a;
//        a = abs(a);
//        b = abs(b);

        while (b != 0) {
            int t = a % b;
            a = b;
            b = t;
        }
        return a;
    }

    public static BigInteger lcm(BigInteger a, BigInteger b) {
        return a.divide(gcd(a, b)).multiply(b);
    }

    public static long lcm(long a, long b) {
        return a / gcd(a, b) * b;
    }

    public static int lcm(int a, int b) {
        return a / gcd(a, b) * b;
    }

    public static boolean isPrime(long n) {
        initPrimesList();
        if (n <= lastCachedPrime) {
            return Collections.binarySearch(cachedPrimes, n) >= 0;
        }

        return isPrime(getCachedPrimes(), cachedPrimes.size(), n);
    }

    public static int doEratosthen(long primes[], int range) {
        return doEratosthen(primes, new boolean[range]);
    }

    /**
     *
     * @param primes - outArray for caching primes, null if no need caching
     * @param outArray - outArray to use for sieve
     * @return numer of cached primes
     */
    public static int doEratosthen(long primes[], boolean[] outArray) {
        int primesCount = 0;
        Arrays.fill(outArray, true);

        outArray[0] = outArray[1] = false;

        int n = outArray.length - 1;
        for (long i = 2; i <= n; ++i) {
            if (outArray[(int) i]) {
                if (primes != null) {
                    primes[primesCount++] = i;
                    if (primesCount == primes.length) {
                        return primesCount;
                    }
                }

                for (long j = i * i; j <= n; j += i) {
                    outArray[(int) j] = false;
                }
            }
        }

        return primesCount;
    }

    public static boolean isPrime(long cachedPrimes[], int primesCount, long n) {
        if (n == 2) {
            return true;
        }

        int i;
        for (i = 0; i < primesCount && cachedPrimes[i] * cachedPrimes[i] <= n; ++i) {
            if (n % cachedPrimes[i] == 0) {
                return false;
            }
        }

        if (i == primesCount) {
            for (long j = cachedPrimes[primesCount - 1]; j * j <= n; j += 2) {
                if (n % j == 0) {
                    return false;
                }
            }
        }

        return true;
    }

    public static long getProperDivisorsSum(long n) {
        return getDivisorsSum(n) - n;
    }

    public static long getDivisorsSum(long n) {
        long[] ps = getPrimesBetween(0, (long)(Math.sqrt(n) + 2));
        long sum = 1;
        long m = n;
        for (long p : ps) {
            long dp = 1;
            while (m % p == 0) {
                m /= p;
                dp = dp * p + 1;
            }
            sum *= dp;
        }

        //some prime's left
        if (m > 1) {
            sum *= (m + 1);
        }
        return sum;
    }

    public static long getDivisorsCount(long n) {
        if (n == 1) {
            return 1;
        }
        long res = 2; // 1 and n
        for (long i = 2; i * i <= n; ++i) {
            if (n % i == 0) {
                long j = n / i;
                res += (i == j) ? 1 : 2;
            }
        }

        return res;
    }

    public static ArrayList<Long> getDivisors(long n) {
        ArrayList<Long> all = new ArrayList<Long>();

        all.add(1L);

        if (n == 1) {
            return all;
        }

        all.add(n);
        for (long i = 2; i * i <= n; ++i) {
            if (n % i == 0) {
                long j = n / i;

                all.add(i);
                if (j != i) {
                    all.add(j);
                }
            }
        }

        Collections.sort(all);

        return all;
    }

    public static ArrayList<Long> getPrimeDivisors(long n) {
        initPrimesList();
        ArrayList<Long> all = new ArrayList<Long>();

        long sqN = (long) Math.sqrt(n);

        for (long p : cachedPrimes) {
            if (n % p == 0) {
                all.add(p);
                while (n % p == 0) {
                    n /= p;
                }
            }

            if (p > sqN) {
                if (n != 1) {
                    all.add(n);
                }
                return all;
            }
        }

        long last = lastCachedPrime;
        assert ((last & 1) != 0);

        for (long i = last + 2; i * i <= n; i += 2) {
            if (n % i == 0 && isPrime(i)) {
                all.add(i);
                while (n % i == 0) {
                    n /= i;
                }
            }
        }

        if (n != 1) {
            all.add(n);
        }

        return all;
    }

    public static TreeMap<Long, Integer> factor(long n) {
        initPrimesList();

        TreeMap<Long, Integer> all = new TreeMap<>();

        long sqN = (long) Math.sqrt(n);

        for (long p : cachedPrimes) {
            if (n % p == 0) {
                int cnt = 0;
                while (n % p == 0) {
                    n /= p;
                    ++cnt;
                }
                all.put(p, cnt);
            }

            if (p > sqN) {
                if (n != 1) {
                    all.put(n, 1);
                }
                return all;
            }
        }

        long last = lastCachedPrime;
        assert ((last & 1) != 0);

        for (long i = last + 2; i * i <= n; i += 2) {
            if (n % i == 0 && isPrime(i)) {
                int cnt = 0;
                while (n % i == 0) {
                    n /= i;
                    ++cnt;
                }
                all.put(i, cnt);
            }
        }

        if (n != 1) {
            all.put(n, 1);
        }

        return all;
    }

//    private static int MAX_PRIMES_TO_CACHE = 1500000;
    private static int MAX_PRIMES_TO_CACHE = 5800000;
//    private static int MAX_PRIMES_TO_CACHE = 1300000;
    public static void setMaxPrimesToCache(int maxPrimesToCache) {
        MAX_PRIMES_TO_CACHE = maxPrimesToCache;
    }

    public static long[] primes(int maxCount) {
        setMaxPrimesToCache(maxCount);
        return getCachedPrimesInternal();
    }

    private static void initPrimesList() {
        if (cachedPrimes != null) {
            return;
        }

        cachedPrimes = new ArrayList<Long>(MAX_PRIMES_TO_CACHE);
        try {
            BufferedReader inFile = new BufferedReader(new FileReader("./files/primes.dat"));

            String line = inFile.readLine();
            while (line != null && cachedPrimes.size() < MAX_PRIMES_TO_CACHE) {
                cachedPrimes.add(Long.parseLong(line));
                line = inFile.readLine();
            }
        } catch (FileNotFoundException e) {
            cachedPrimes.add(2L);
            cachedPrimes.add(3L);
        } catch (IOException ignored) {
        }

        lastCachedPrime = cachedPrimes.get(cachedPrimes.size() - 1);
    }

    public static long[] getCachedPrimes() {
        initPrimesList();

        int n = cachedPrimes.size();
        long[] primes = new long[n];
        for (int i = 0; i < n; ++i) {
            primes[i] = cachedPrimes.get(i);
        }

        return primes;
    }

    private static long cachedPrimesArr[];
    public static long[] getCachedPrimesInternal() {
        if (cachedPrimesArr != null) {
            return cachedPrimesArr;
        }
        initPrimesList();

        int n = cachedPrimes.size();
        cachedPrimesArr = new long[n];
        for (int i = 0; i < n; ++i) {
            cachedPrimesArr[i] = cachedPrimes.get(i);
        }

        return cachedPrimesArr;
    }


    public static long[] getPrimesBetween(long beg, long end) {
        initPrimesList();

        long lastPrime = cachedPrimes.get(cachedPrimes.size() - 1);
        long n = lastPrime + 2;
        for (; n <= end; n += 2) {
            if (isPrime(n)) {
                cachedPrimes.add(n);
            }
        }

        return getCachedPrimesBetween(beg, end);
    }

    private static long[] getCachedPrimesBetween(long beg, long end) {
        initPrimesList();

        int b = 0;
        while (cachedPrimes.get(b) < beg) {
            b++;
        }
        int e = b;
        while (e < cachedPrimes.size() && cachedPrimes.get(e) <= end) {
            e++;
        }
        --e;

        int cnt = e - b + 1;
        long ret[] = new long[cnt];
        for (int i = 0; i < cnt; ++i) {
            ret[i] = cachedPrimes.get(b + i);
        }
        return ret;
    }

    public static boolean hasCommonDivisors(long a, long b) {
        long g = gcd(a, b);
        return g > 1;
    }
//    Fuck... how could I write this!..
//    public static boolean hasCommonDivisors(long a, long b) {
//        ArrayList<Long> divs1 = getDivisors(a);
//        divs1.remove(0);
//        ArrayList<Long> divs2 = getDivisors(b);
//
//        for (long div : divs1) {
//            if (divs2.contains(div)) {
//                return true;
//            }
//        }
//
//        return false;
//    }
//
    public static boolean isExactSquare(long n) {
        long sqrt = (long) Math.sqrt(n);
        return (sqrt * sqrt == n);
    }

    /**
     * @deprecated use ApintMath.sqrt instead
     * @param n
     * @return
     * @see ApintMath#sqrt(org.apfloat.Apint)
     */
    public static BigInteger sqrt(final BigInteger n) {
        if (n.equals(BigInteger.ZERO)) {
            return n;
        }

        // use a half-decent initial approximation
        BigInteger x_new = BigInteger.ONE.shiftLeft((n.bitLength() + 1) / 2);
        BigInteger x_old;

        // finish off with Newton-Raphson: x_new = (n/x_old + x_old)/2
        do {
            x_old = x_new;
            x_new = n.divide(x_old).add(x_old).shiftRight(1);
        } while (x_old.compareTo(x_new) > 0);

        return x_new;
    }

    public static boolean isExactSquare(BigInteger n) {
        return ApintMath.sqrt(new Apint(n))[0].equals(Apint.ZERO);
//        BigInteger sqrt = sqrt(n);
//        return (sqrt.multiply(sqrt).equals(n));
    }

    /**
     * Euler's totient function
     */
    public static long totient(long n) {
        return phi(n);
    }

    public static long phi(long n) {
        if (n == 1) {
            return 1;
        }
        long res = n;
        ArrayList<Long> divs = getPrimeDivisors(n);
        for (long div : divs) {
            res = (res - res / div);
        }

        return res;
    }

    public static boolean isPowerOfTwo(long x) {
        return (x != 0) && ((x & (x - 1)) == 0);
    }

    public static long pow(long n, int p) {
        if (p==1) return n;
        if (p==2) return n*n;
        if (p==3) return n*n*n;

        long r = 1;
        for (int i = 0; i < p; ++i) {
            r *= n;
        }
        return r;
    }

    public static boolean isBitSet(int n, int bInd) {
        return (n & (1<<bInd)) != 0;
    }

    public static int setBit(int n, int bInd) {
        return n | (1<<bInd);
    }

    public static int unSetBit(int n, int bInd) {
        return n & (~(1<<bInd));
    }

    public static boolean isBitSet(long n, int bInd) {
        return (n & (1L<<bInd)) != 0;
    }

    public static long setBit(long n, int bInd) {
        return n | (1L<<bInd);
    }

    public static long unSetBit(long n, int bInd) {
        return n & (~(1L<<bInd));
    }

    public static int bitCount(long n) {
        int res = 0;
        while (n != 0) {
            ++res;
            n &= n - 1;
        }

        return res;
    }

    //see here: https://graphics.stanford.edu/~seander/bithacks.html#IntegerLogLookup
    public static int mostSignificantBit(int n) {
        int tt = n >> 16;
        if (tt != 0) {
            int t = tt >> 8;
            return (t != 0) ? 24 + LogTable256[t] : 16 + LogTable256[tt];
        } else {
            int t = n >> 8;
            return (t != 0) ? 8 + LogTable256[t] : LogTable256[n];
        }
    }

    public static int mostSignificantPowerTwo(int n) {
        return 1 << mostSignificantBit(n);
    }

    public static int sumOfDigits(long n) {
        int sum = 0;
        while (n != 0) {
            sum += n%10;
            n /= 10;
        }

        return sum;
    }

    public static int signum(long a) {
        return a > 0
               ? 1
               : a < 0
                 ? -1
                 : 0;
    }

    public static int numberOfDigits(long n) {
        return (int)floor( log10( abs( n != 0 ? n : 1 ) ) ) + 1;
    }

    public static boolean isProbablePrime(long n, int certainty) {
        return valueOf(n).isProbablePrime(certainty);
    }

    public static BigInteger bi(long n) {
        return valueOf(n);
    }

    /*
     * http://en.wikipedia.org/wiki/Cornacchia%27s_algorithm
     * http://www.schorn.ch/howto.html
     */
    public static long[] decomposePrimeAsTwoSquares(long p) {
        if (p == 2) {
            return new long[]{1, 1};
        }
        assert p%4 == 1;

        long b;
        if (p%8 == 5) {
            b = 2;
        } else if (p%3 == 2) {
            b = 3;
        } else {
            b = 5;
            int i = 2;
            while (modPow(b, (p-1)/2, p) == 1) {
                b = getCachedPrimesInternal()[++i];
            }
        }

        b = modPow(b, (p-1)/4, p);
        // b is now a quadratic root of -1 mod p, i.e. b^2 = -1 mod p
        long a = p;
        while (b*b > p) {
            long t = a % b;
            a = b;
            b = t;
        }
        return new long[] {b, a%b};
    }

    /**
     * solving ax + by = c
     * see http://en.wikipedia.org/wiki/Extended_Euclidean_algorithm#Iterative_method_2
     */
    public static long[] solveLinearDiaophanteEq(long a, long b, long c) {
        assert (a != 0 || b != 0);
        if (c < 0) {
            a = -a;
            b = -b;
            c = -c;
        }

        int sa = signum(a);
        int sb = signum(b);

        if (a < 0) {
            a = -a;
        }

        if (b < 0) {
            b = -b;
        }

        long x = 0;
        long lastx = 1;
        long y = 1;
        long lasty = 0;
        long q, r;
        while (b != 0) {
            q = a / b;

            r = a - q*b;
            a = b;
            b = r;

            r = lastx - q*x;
            lastx = x;
            x = r;

            r = lasty - q*y;
            lasty = y;
            y = r;
        }

        if (c % a != 0) {
            //keep it simple for now
            throw new IllegalStateException("No solutions!!");
        }

        long k = c/a;

        return new long[]{k * sa * lastx, k * sb * lasty};
    }

    public static BigInteger[] bSolveLinearDiaophanteEq(BigInteger a, BigInteger b, BigInteger c) {
        if (c.compareTo(ZERO) < 0) {
            a = a.negate();
            b = b.negate();
            c = c.negate();
        }

        int sa = a.signum();
        int sb = b.signum();

        if (a.compareTo(ZERO) < 0) {
            a = a.negate();
        }

        if (b.compareTo(ZERO) < 0) {
            b = b.negate();
        }

        BigInteger x = ZERO;
        BigInteger lastx = ONE;
        BigInteger y = ZERO;
        BigInteger lasty = ONE;
        BigInteger q, r;
        BigInteger qr[];
        while (!b.equals(ZERO)) {
            qr = a.divideAndRemainder(b);

            q = qr[0];
            r = qr[1];

            a = b;
            b = r;

            r = lastx.subtract(q.multiply(x));
            lastx = x;
            x = r;

            r = lasty.subtract(q.multiply(y));
            lasty = y;
            y = r;
        }

        if (!c.mod(a).equals(ZERO)) {
            //keep it simple for now
            throw new IllegalStateException("No solutions!!");
        }

        BigInteger k = c.divide(a);

        lastx = k.multiply(lastx);
        lasty = k.multiply(lasty);

        if (sa < 0) {
            lastx = lastx.negate();
        }

        if (sb < 0) {
            lasty = lasty.negate();
        }

        return new BigInteger[]{lastx, lasty};
    }
    
    //http://en.wikipedia.org/wiki/Chinese_remainder_theorem
    public static long solveChineseRemainderTheorem(long[] a, long[] n, int length) {
        return garnerRestore(a, n, length);
    }

    //it's actually solveChineseRemainderTheorem
    //from: https://sites.google.com/site/indy256/algo/euclid
    public static long simpleRestore(long[] a, long[] p, int length) {
        long res = a[0];
        long m = 1;
        for (int i = 1; i < length; i++) {
            m *= p[i - 1];
            while (res % p[i] != a[i])
                res += m;
        }
        return res;
    }

    //it's actually solveChineseRemainderTheorem
    //from: https://sites.google.com/site/indy256/algo/euclid
    public static long garnerRestore(long[] a, long[] p, int length) {
        long[] x = new long[length];
        for (int i = 0; i < length; ++i) {
            x[i] = a[i];
            for (int j = 0; j < i; ++j) {
                x[i] = (int) modInverse(p[j], p[i]) * (x[i] - x[j]);
                x[i] = (x[i] % p[i] + p[i]) % p[i];
            }
        }
        long res = x[0];
        int m = 1;
        for (int i = 1; i < length; i++) {
            m *= p[i - 1];
            res += x[i] * m;
        }
        return res;
    }

    /**
     * returnds x from solving ax + n*y == 1
     */
    public static long inverseEuclid(long a, long n) {
        long ret[] = solveLinearDiaophanteEq(a, n, 1);
        return ret[0];
    }

    public static long modInverse(long a, long mod) {
        long inv = inverseEuclid(a, mod);
        return inv < 0 ? mod + inv : inv;
    }

    public static long modInverse2(long a, long mod) {
        long inv = inverseEuclid(a, -mod);
        return inv;
    }

    public static long modPow(long n, long pow, long mod) {
        assert (n >= 0 || pow >= 0);

        if (pow == 0) {
            return 1;
        }

        long r = 1;
        n = n % mod;
        while (pow > 0) {
            if ((pow & 1) == 1) {
                r = r * n % mod;
            }
            pow >>= 1;
            n = n*n % mod;
        }

        return r;
    }

    //using: http://en.wikipedia.org/wiki/Shanks-Tonelli_algorithm
    public static long modSqrt(long n, long p) {
        if (p%4 == 3) {
            return modPow(n, (p+1) / 4, p);
        }

        // split p-1 into Q * 2^S
        int S = 0;
        long Q = p - 1;
        while (Q % 2 == 0) {
            Q /= 2;
            ++S;
        }

        // find a non-residue modulo p
        long z = 2;
        while (modPow(z, (p-1)/2, p) == 1) {
            z = 2 + (long)(Math.random()*(p-3));
        }

        // main loop
        long c = modPow(z, Q, p);
        long R = modPow(n, (Q+1)/2, p);
        long t = modPow(n, Q, p);
        int M = S;
        while (t != 1) {
            int i = 0;
            long tn = t;
            while (tn != 1) {
                ++i;
                tn = (tn*tn)%p;
            }

            long b = modPow(c, 1 << (M - i - 1), p);
            R = (R*b) % p;
            c = b*b % p;
            //t = t*b^2;
            t = (t*c)%p;

            M = i;
        }

        return R;
    }

    public static long[] computePhis(int n) {
        long phi[] = new long[n+1];
        phi[1] = 1;
        for (int i = 2; i <= n; ++i) {
            if (phi[i] == 0) {
                phi[i] = i - 1;
                for (int j = 2; i * j <= n; ++j) {
                    if (phi[j] != 0) {
                        int q = j;
                        int f = i - 1;
                        while (q % i == 0) {
                            f *= i;
                            q /= i;
                        }
                        phi[i * j] = f * phi[q];
                    }
                }
            }
        }
        return phi;
    }
}
