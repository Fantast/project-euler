package utils;

public class IntPoint {

    public int x;
    public int y;

    public IntPoint(int x, int y) {
        super();
        this.x = x;
        this.y = y;
    }

    @Override
    public int hashCode() {
        return new Integer(x + y).hashCode();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof IntPoint)) {
            return false;
        }
        IntPoint oth = (IntPoint) obj;
        return oth.x == x && oth.y == y;
    }

    public String toString() {
        return "{" + x + "," + y + "}";
    }
}
