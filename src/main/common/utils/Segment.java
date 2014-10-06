package utils;

/**
* Created by dzmitry on 06.10.14.
*/
public class Segment {
    public final double x1;
    public final double y1;
    public final double x2;
    public final double y2;

    public Segment(double x1, double y1, double x2, double y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public int x1() {
        return (int) x1;
    }

    public int y1() {
        return (int) y1;
    }

    public int x2() {
        return (int) x2;
    }

    public int y2() {
        return (int) y2;
    }

    @Override
    public String toString() {
        return "{" + x1 + "," + y1 + "," + x2 + "," + y2 + '}';
    }
}
