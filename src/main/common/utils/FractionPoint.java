package utils;

public class FractionPoint {

    public LongFraction x;
    public LongFraction y;

    public FractionPoint(LongFraction x, LongFraction y) {
        super();
        this.x = x;
        this.y = y;
    }

    @Override
    public int hashCode() {
        return x.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof FractionPoint)) {
            return false;
        }
        FractionPoint oth = (FractionPoint) obj;
        return oth.x.equals(x) && oth.y.equals(y);
    }

    @Override
    public String toString() {
        return "{" + x + "," + y + "}";
    }
}
