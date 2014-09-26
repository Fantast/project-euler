package utils;

import java.math.BigDecimal;
import java.math.BigInteger;

import static utils.MyMath.gcd;

public class Fraction implements Comparable<Fraction> {
    public static final Fraction ZERO = new Fraction(0, 1);
    public static final Fraction ONE = new Fraction(1, 1);
    public BigInteger numer, denom;
    private double value;

    public Fraction(long numer, long denom) {
        this(BigInteger.valueOf(numer), BigInteger.valueOf(denom));
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public Fraction(BigInteger numer, BigInteger denom) {
        // throw Exception if denom==0
        numer.divide(denom);

        if (denom.compareTo(BigInteger.ZERO) < 0) {
            denom = denom.negate();
            numer = numer.negate();
        }
        this.numer = numer;
        this.denom = denom;

        simplify();

        BigDecimal res = new BigDecimal(numer).divide(new BigDecimal(denom), 30, BigDecimal.ROUND_HALF_UP);
        value = res.doubleValue();
    }

    public Fraction reciprocal() {
        return new Fraction(denom, numer);
    }

    private void simplify() {
        BigInteger g = gcd(numer.abs(), denom.abs());
        numer = numer.divide(g);
        denom = denom.divide(g);
    }

    public Fraction add(Fraction b) {
        return new Fraction( numer.multiply(b.denom).add(b.numer.multiply(denom)), denom.multiply(b.denom) );
    }

    public Fraction subtract(Fraction b) {
        return new Fraction( numer.multiply(b.denom).subtract(b.numer.multiply(denom)), denom.multiply(b.denom) );
    }

    public Fraction multiply(Fraction b) {
        return new Fraction( numer.multiply(b.numer), denom.multiply(b.denom) );
    }

    public Fraction divide(Fraction b) {
        return new Fraction( numer.multiply(b.denom), denom.multiply(b.numer) );
    }

    public boolean equals(Object obj) {
        if ( super.equals(obj) ) return true;

        if ( !(obj instanceof Fraction) ) return false;
        Fraction f = (Fraction) obj;


        return numer.equals(BigInteger.ZERO) && f.numer.equals(BigInteger.ZERO) ||
               numer.equals(f.numer) && denom.equals(f.denom);
    }

    public String toString() {
        if ( numer.equals(BigInteger.ZERO) ) return "0";
        if ( denom.equals(BigInteger.ONE) ) return numer.toString();

        return numer.toString() + "/" + denom.toString();
    }

    public int compareTo(Fraction f) {
        if ( this.equals(f) ) return 0;
        return this.subtract(f).numer.compareTo(BigInteger.ZERO);
    }

    public double doubleValue() {
        return value;
    }

    //check if a/b is terminating fraction
    public static boolean isTermniating(long a, long b) {
        b /= gcd(a, b);

        while (b%2 == 0) b /=2;
        while (b%5 == 0) b /=5;

        return b==1;
    }

}
