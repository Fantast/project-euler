package utils.pairs;

public class StringPair implements Comparable<StringPair> {
    public String a;
    public String b;

    public StringPair(String a, String b) {
        this.a = a;
        this.b = b;
    }

    public int hashCode() {
        return (a+b).hashCode();
    }

    public boolean equals(Object obj) {
        if (super.equals(obj)) return true;

        if ( !(obj instanceof StringPair) ) return false;
        StringPair p = (StringPair)obj;

        return a.equals(p.a) && b.equals(p.b);
    }

    public int compareTo(StringPair p) {
        int ret = a.compareTo(p.a);
        if (ret != 0) return ret;
        return b.compareTo(p.b);
    }

    public String toString() {
        return "[" + a + ", " + b + "]";
    }
}
