package utils.pairs;

public class Pair<T1, T2> {
    public T1 a;
    public T2 b;

    public Pair(T1 a, T2 b) {
        this.a = a;
        this.b = b;
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;

        if ( !(obj instanceof Pair) ) return false;
        Pair<T1, T2> p = (Pair)obj;

        if (a==null) return p.a==null;
        if (b==null) return p.b==null;

        if (p.a==null) return false;
        if (p.b==null) return false;

        return a.equals(p.a) && b.equals(p.b);
    }

    public String toString() {
        return "[" + a + ", " + b + "]";
    }
}
