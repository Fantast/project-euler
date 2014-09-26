package utils;

import java.math.BigInteger;

public class BigFraction implements Comparable<BigFraction> {
    public static BigFraction ZERO = new BigFraction(BigInteger.ZERO, BigInteger.ONE);
    public static BigFraction ONE = new BigFraction(BigInteger.ONE, BigInteger.ONE);
    public BigInteger numer, denom;

    public BigFraction(long numer, long denom) {
        this(BigInteger.valueOf(numer), BigInteger.valueOf(denom));
    }

    public BigFraction(BigInteger numer, BigInteger denom) {
        // throw Exception if denom==0
        if (denom.equals(BigInteger.ZERO)) {
            throw new IllegalArgumentException("Denominator must not be 0.");
        }

        if (numer.equals(BigInteger.ZERO)) {
            denom = BigInteger.ONE;
        } else if (denom.compareTo(BigInteger.ZERO) < 0) {
            numer = numer.negate();
            denom = denom.negate();
        }

        this.numer = numer;
        this.denom = denom;
        simplify();
    }

    public BigFraction reciprocal() {
        return new BigFraction(denom, numer);
    }

    private void simplify() {
        BigInteger g = numer.gcd(denom);
        numer = numer.divide(g);
        denom = denom.divide(g);
    }

    public BigFraction add(BigFraction b) {
        return new BigFraction( numer.multiply(b.denom).add(b.numer.multiply(denom)), denom.multiply(b.denom));
    }

    public BigFraction subtract(BigFraction b) {
        return new BigFraction( numer.multiply(b.denom).subtract(b.numer.multiply(denom)), denom.multiply(b.denom));
    }

    public BigFraction multiply(BigFraction b) {
        return new BigFraction( numer.multiply(b.numer), denom.multiply(b.denom) );
    }

    public BigFraction divide(BigFraction b) {
        return new BigFraction( numer.multiply(b.denom), denom.multiply(b.numer) );
    }

    @Override
    public int hashCode() {
        return numer.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if ( super.equals(obj) ) return true;

        if ( !(obj instanceof BigFraction) ) return false;
        BigFraction f = (BigFraction) obj;

        return numer.equals(f.numer) && denom.equals(f.denom);
    }

    @Override
    public String toString() {
        if ( numer.equals(BigInteger.ZERO)) return "0";
        if ( denom.equals(BigInteger.ONE)) return numer.toString();

        return numer.toString() + "/" + denom.toString();
    }

    @Override
    public int compareTo(BigFraction f) {
        if ( this.equals(f) ) return 0;
        return this.subtract(f).numer.compareTo(BigInteger.ZERO);
    }
}
