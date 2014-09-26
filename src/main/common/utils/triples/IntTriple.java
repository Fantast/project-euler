package utils.triples;

public class IntTriple implements Comparable<IntTriple> {
    public int a;
    public int b;
    public int c;

    public IntTriple(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public int hashCode() {
        return new Integer(a+b+c).hashCode();
    }

    public boolean equals(Object obj) {
        if (super.equals(obj)) return true;

        if ( !(obj instanceof IntTriple) ) return false;
        IntTriple p = (IntTriple)obj;

        return a==p.a && b==p.b && c==p.c;
    }

    public int compareTo(IntTriple p) {
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
