package utils;

public class Geometry {

    public final static double EPS = 1e-6;

    public static long scalarProduct(Point a1, Point a2, Point b1, Point b2) {
        return (a2.x - a1.x)*(b2.x - b1.x) + (a2.y - a1.y)*(b2.y - b1.y);
    }
    
    public static long twistedProduct(Point a1, Point a2, Point b1, Point b2) {
        return (a2.x - a1.x)*(b2.y - b1.y) - (a2.y - a1.y)*(b2.x - b1.x);
    }

    public static boolean intersects(Point a1, Point a2, Point b1, Point b2) {
        long z1 = twistedProduct(a1, a2, a1, b1);
        long z2 = twistedProduct(a1, a2, a1, b2);

        long z3 = twistedProduct(b1, b2, b1, a1);
        long z4 = twistedProduct(b1, b2, b1, a2);

        return z1*z2 <= 0 || z3*z4 <= 0;
    }

    public static FractionPoint getTrueIntersection(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) {
        Point p1 = new Point(x1, y1);
        Point p2 = new Point(x2, y2);
        Point p3 = new Point(x3, y3);
        Point p4 = new Point(x4, y4);

        return getTrueIntersection(p1, p2, p3, p4);
    }

    public static FractionPoint getTrueIntersection(Point a1, Point a2, Point b1, Point b2) {
        long z1 = twistedProduct(a1, a2, a1, b1);
        long z2 = twistedProduct(a1, a2, a1, b2);

        long z3 = twistedProduct(b1, b2, b1, a1);
        long z4 = twistedProduct(b1, b2, b1, a2);

        if (z1*z2 >= 0 || z3*z4 >= 0) return null;

// p1M = p1p2 * [p3p4, p3p1] / [p1p2, p3p4];

        long z = twistedProduct(a1, a2, b1, b2);
        LongFraction mx = new LongFraction(a1.x*z + (a2.x - a1.x) * z3, z);
        LongFraction my = new LongFraction(a1.y*z + (a2.y - a1.y) * z3, z);

        return new FractionPoint(mx, my);
    }

    public static double twistedProduct(PointD a1, PointD a2, PointD b1, PointD b2) {
        return (a2.x - a1.x) * (b2.y - b1.y) - (a2.y - a1.y) * (b2.x - b1.x);
    }

    public static PointD intersect(Segment sa, Segment sb) {
        return intersect(sa.x1, sa.y1, sa.x2, sa.y2, sb.x1, sb.y1, sb.x2, sb.y2);
    }

    public static PointD intersect(double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4) {
        PointD p1 = new PointD(x1, y1);
        PointD p2 = new PointD(x2, y2);
        PointD p3 = new PointD(x3, y3);
        PointD p4 = new PointD(x4, y4);

        return intersect(p1, p2, p3, p4);
    }

    public static PointD intersect(PointD a1, PointD a2, PointD b1, PointD b2) {
        double z1 = twistedProduct(a1, a2, a1, b1);
        double z2 = twistedProduct(a1, a2, a1, b2);

        double z3 = twistedProduct(b1, b2, b1, a1);
        double z4 = twistedProduct(b1, b2, b1, a2);

        double z12 = z1 * z2;
        double z34 = z3 * z4;
        if (Math.abs(z1) > EPS && Math.abs(z2) > EPS && Math.abs(z3) > EPS && Math.abs(z4) > EPS) {
            if (z12 > 0 || z34 > 0) return null;
        }

// p1M = p1p2 * [p3p4, p3p1] / [p1p2, p3p4];

        double z = twistedProduct(a1, a2, b1, b2);
        double mx = (a1.x * z + (a2.x - a1.x) * z3) / z;
        double my = (a1.y * z + (a2.y - a1.y) * z3) / z;

        return new PointD(mx, my);
    }
}
