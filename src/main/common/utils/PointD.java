package utils;

public class PointD {

    public double x;
    public double y;

    public PointD(double x, double y) {
        super();
        this.x = x;
        this.y = y;
    }

    @Override
    public int hashCode() {
        return new Double(x + y).hashCode();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof PointD)) {
            return false;
        }
        PointD oth = (PointD) obj;
        return oth.x == x && oth.y == y;
    }

    public String toString() {
        return "{" + x + "," + y + "}";
    }
}
