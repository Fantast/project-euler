package utils;

public class Geometry {
    
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
}
