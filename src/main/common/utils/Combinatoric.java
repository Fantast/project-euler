package utils;

import java.math.BigInteger;

import static java.lang.Math.max;
import static java.math.BigInteger.ONE;
import static java.math.BigInteger.ZERO;

public class Combinatoric {
    private static BigInteger facts[] = null;

    private static BigInteger d[][] = new BigInteger[102][102];


    // Partial derangement (Rencontres numbers)
    // http://en.wikipedia.org/wiki/Partial_derangement
    public static BigInteger D(int n, int k) {
        if (d[n][k] != null) return d[n][k];
        
        initFactorials( max(n, k) );

        if (k == 0) {
            if (n==0) return d[n][k] = ONE;
            if (n==1) return d[n][k] = ZERO;

            return d[n][k] = BigInteger.valueOf(n-1).multiply(D(n-1,0).add(D(n-2,0)));
        }
        
        return d[n][k] = C(n, k).multiply(D(n-k, 0));
    }

    public static BigInteger C(int n, int r) {
        if (r > n) return ZERO;
        initFactorials( max(n, r) );
        return facts[n].divide(facts[r]).divide(facts[n-r]);
    }

    public static BigInteger factorial(int n) {
        initFactorials(n);
        return facts[n];
    }

    private static void initFactorials(int maxN) {
        if (facts != null && maxN < facts.length) return;

        if (facts == null) {
            int length = (maxN > 101) ? maxN+10 : 101;
            facts = new BigInteger[length];
        } else if (maxN >= facts.length) {
            facts = new BigInteger[maxN+10];
        }

        facts[0] = BigInteger.ONE;
        for (int i = 1; i < facts.length; ++i) {
            facts[i] = facts[i-1].multiply(BigInteger.valueOf(i));
        }
    }
}
