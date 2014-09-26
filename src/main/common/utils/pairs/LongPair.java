package utils.pairs;

public class LongPair implements Comparable<LongPair> {
    public long a;
    public long b;

    public LongPair(long a, long b) {
        this.a = a;
        this.b = b;
    }

    public int hashCode() {
        return new Long(a+b).hashCode();
    }

    public boolean equals(Object obj) {
        if (super.equals(obj)) return true;

        if ( !(obj instanceof LongPair) ) return false;
        LongPair p = (LongPair)obj;

        return a==p.a && b==p.b;
    }

    public int compareTo(LongPair p) {
        int ret = (a < p.a) ? -1 : (a==p.a ? 0 : 1);
        if (ret != 0) return ret;
        return (b < p.b) ? -1 : (b==p.b ? 0 : 1);
    }

    public String toString() {
        return "[" + a + ", " + b + "]";
    }
}
