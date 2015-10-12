package utils;

public class Point {

    public long x;
    public long y;

    public Point(long x, long y) {
        super();
        this.x = x;
        this.y = y;
    }

    public FractionPoint toFractionPoint() {
        return new FractionPoint(new LongFraction(x), new LongFraction(y));
    }

    @Override
    public int hashCode() {
        return new Long(x + y).hashCode();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Point)) {
            return false;
        }
        Point oth = (Point) obj;
        return oth.x == x && oth.y == y;
    }

    public String toString() {
        return "{" + x + "," + y + "}";
    }
}
