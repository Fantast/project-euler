package utils;

public class LongFraction implements Comparable<LongFraction> {
    public static LongFraction ZERO = new LongFraction(0, 1);
    public static LongFraction ONE = new LongFraction(1, 1);
    public long numer, denom;
    private Double value;

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    public double doubleValue() {
        return value;
    }

    public LongFraction(long numer) {
        this(numer, 1);
    }

    public LongFraction(long numer, long denom) {
        // throw Exception if denom==0
        long tmp = numer / denom;

        if (denom < 0) {
            numer = -numer;
            denom = -denom;
        }
        this.numer = numer;
        this.denom = denom;
        simplify();
        value = (double)numer / (double)denom;
    }

    public LongFraction reciprocal() {
        return new LongFraction(denom, numer);
    }

    private void simplify() {
        long g = gcd(numer, denom);
        numer /= g;
        denom /= g;
    }

    private long gcd(long a, long b) {
        if ( a<0 ) return gcd(-a, b);
        if ( b<0 ) return gcd(a, -b);
        return MyMath.gcd(a, b);
    }

    public LongFraction add(LongFraction b) {
        return new LongFraction( numer*b.denom + b.numer*denom, denom * b.denom );
    }

    public LongFraction subtract(LongFraction b) {
        return new LongFraction( numer*b.denom - b.numer*denom, denom * b.denom );
    }

    public LongFraction multiply(LongFraction b) {
        return new LongFraction( numer*b.numer, denom * b.denom );
    }

    public LongFraction divide(LongFraction b) {
        return new LongFraction( numer*b.denom, denom * b.numer );
    }

    public LongFraction divide(long n) {
        return new LongFraction( numer, denom * n );
    }

    public LongFraction multiply(long n) {
        return new LongFraction( numer*n, denom);
    }

    @Override
    public boolean equals(Object obj) {
        if ( super.equals(obj) ) return true;

        if ( !(obj instanceof LongFraction) ) return false;
        LongFraction f = (LongFraction) obj;


        return numer==0 && f.numer==0 ||
               numer==f.numer && denom==f.denom;
    }

    @Override
    public String toString() {
        if ( numer==0 ) return "0";
        if ( denom==1 ) return String.valueOf(numer);

        return String.valueOf(numer) + "/" + String.valueOf(denom);
    }

    public int compareTo(LongFraction f) {
        if ( this.equals(f) ) return 0;
        return new Double(this.subtract(f).numer).compareTo(0.0);
    }
}
