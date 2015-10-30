package utils.triples;

public class LongTriple implements Comparable<LongTriple> {
    public final long a;
    public final long b;
    public final long c;

    private final int hashCode;

    public LongTriple(long a, long b, long c) {
        this.a = a;
        this.b = b;
        this.c = c;
        hashCode = new Long(a+b+c).hashCode();
    }

    public int hashCode() {
        return hashCode;
    }

    public boolean equals(Object obj) {
        if (super.equals(obj)) return true;

        if ( !(obj instanceof LongTriple) ) return false;
        LongTriple p = (LongTriple)obj;

        return a==p.a && b==p.b && c==p.c;
    }

    public int compareTo(LongTriple p) {
        int ret = (a < p.a) ? -1 : (a==p.a ? 0 : 1);
        if (ret != 0) return ret;

        ret = (b < p.b) ? -1 : (b==p.b ? 0 : 1);
        if (ret != 0) return ret;

        return (c < p.c) ? -1 : (c==p.c ? 0 : 1);
    }

    public String toString() {
        return "[" + a + ", " + b + ", " + c + "]";
    }
}
