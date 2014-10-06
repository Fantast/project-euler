package solved;

import tasks.AbstractTask;
import tasks.Tester;
import utils.Geometry;
import utils.PointD;
import utils.log.Logger;

//Answer : 1725323624056
// can be much faster, if to count points in a sector we sort all points by polar angle
// and use binary search to find edge points
public class Task_184 extends AbstractTask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_184());
        Logger.close();
    }

    public int r = 105;
    public int r2 = r*r;

    PointD ps[] = new PointD[40000];
    PointD vi[][] = new PointD[40000][220];

    PointD ci1[] = new PointD[220];
    PointD ci2[] = new PointD[220];

    PointD origin = new PointD(0, 0);

    int pc = 0;
    public void solving() {
        System.out.println("Generate points...");
        for (int vx = -r+1; vx < r; ++vx) {
            double vy = Math.sqrt(r2 - vx*vx);
            ci1[vx + r] = new PointD(vx, vy);
            ci2[vx + r] = new PointD(vx, -vy);
        }

        for (int x = -r; x < r; ++x) {
            int x2 = x*x;
            for (int y = -r; y < r; ++y) {
                if (x2 + y*y < r2) {
                    PointD point = new PointD(x, y);
                    ps[pc] = point;

                    for (int vx = -r+1; vx < r; ++vx) {
                        vi[pc][vx + r] = findVertIntersection(point, vx);
                    }

                    pc++;
                }
            }
        }
        System.out.println(pc);

        System.out.println("Counting...");

        long res = 0;
        for (int a = 0; a < pc; ++a) {
            progress100(a);
            PointD pa = ps[a];
            for (int b = a+1; b < pc; ++b) {
                PointD pb = ps[b];
//                System.out.println("---------------------------------");
//                System.out.println(pa + " " + pb);

                for (int x = -r+1; x < r; ++x) {
                    long dr = 0;
                    boolean collinear = dequals(0, Math.abs(Geometry.twistedProduct(origin, pa, origin, pb)));
                    if (!collinear) {
                        if (x == 0) {
                            if ((pa.x < 0 && pb.x > 0 || pa.x > 0 && pb.x < 0)) {
                                dr = r - 1;
                            }
                        } else {
                            PointD va = vi[a][x + r];
                            PointD vb = vi[b][x + r];

                            PointD cr1 = ci1[x + r]; // +
                            PointD cr2 = ci2[x + r]; // -

                            if (va != null && vb != null) {
                                double mny = Math.min(va.y, vb.y);
                                double mxy = Math.max(va.y, vb.y);

                                if (dequals(mxy, cr2.y) || mxy < cr2.y) {
//                                    dr = 0;
                                } else if (dequals(mny, cr1.y) || mny > cr1.y) {
//                                    dr = 0;
                                } else {
                                    mxy = Math.min(mxy, cr1.y);
                                    mny = Math.max(mny, cr2.y);

                                    dr = between(mny, mxy);
                                }
                            } else if (va != null) {
                                dr = singlePoint(va, pa, pb, cr1, cr2);
                            } else if (vb != null) {
                                dr = singlePoint(vb, pb, pa, cr1, cr2);
                            }
                        }
                    }

                    if (dr != 0) {
                        res += dr;
//                        System.out.println("  x = " + x + ": " + dr);
                    }
                }
            }
        }
        System.out.println(res);
        System.out.println(res/3);
    }

    private long singlePoint(PointD va, PointD pa, PointD pb, PointD cr1, PointD cr2) {
        double z1 = Geometry.twistedProduct(origin, pa, origin, pb);
        double z2 = Geometry.twistedProduct(origin, pa, origin, cr1);
        double z3 = Geometry.twistedProduct(origin, pa, origin, cr2);
        double z12 = z1*z2;
        double z13 = z1*z3;

        if (dequals(va.y, cr2.y)) {
            if (z12 >= 0) {
                return 0;
            }
            return between(cr1.y, cr2.y);
        }

        if (dequals(va.y, cr1.y)) {
            if (z13 >= 0) {
                return 0;
            }

            return between(cr1.y, cr2.y);
        }

        if (va.y < cr1.y && va.y > cr2.y) {
            if (z12 <= 0) {
                return between(va.y, cr1.y);
            } else {
                return between(va.y, cr2.y);
            }
        }

//        if (va.y > cr1.y || va.y < cr2.y) {
            if (z12 >= 0) {
                return 0;
            }
            return between(cr1.y, cr2.y);
//        }
    }

    private long between(double y1, double y2) {
        double mny = Math.min(y1, y2);
        double mxy = Math.max(y1, y2);

        int mni = (int) mny;
        int mxi = (int) mxy;

        if (mxy < 0 || dequals(mxi, mxy)) {
            --mxi;
        }

        if (mny > 0 || dequals(mni, mny)) {
            ++mni;
        }

        return Math.max(0, mxi - mni + 1);
    }

    private boolean dequals(double a, double b) {
        return Math.abs(a - b) < 1e-7;
    }

    private PointD findVertIntersection(PointD p, int vx) {
        if (p.x == 0) {
            return null;
        }

        if (p.x < 0 && vx < 0) {
            return null;
        }

        if (p.x > 0 && vx > 0) {
            return null;
        }

        if (p.y == 0) {
            return new PointD(vx, 0);
        }

        double vy = p.y * vx / p.x;

        return new PointD(vx, vy);
    }
}
