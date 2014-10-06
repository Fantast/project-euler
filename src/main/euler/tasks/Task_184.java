package tasks;

import utils.Point;
import utils.PointD;
import utils.log.Logger;

//Answer :
public class Task_184 extends AbstractTask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_184());
        Logger.close();
    }

    public int r = 105;
    public int r2 = r*r;

    Point ps[] = new Point[40000];
    PointD vi[][] = new PointD[40000][220];

    PointD ci[] = new PointD[220];
    PointD ci2[] = new PointD[220];

    int pc = 0;
    public void solving() {
        System.out.println("Generate points...");
        for (int vx = -r+1; vx < r; ++vx) {
            double vy = Math.sqrt(r2 - vx*vx);
            ci[vx + r] = new PointD(vx, vy);
            ci2[vx + r] = new PointD(vx, -vy);
        }

        for (int x = -r; x < r; ++x) {
            int x2 = x*x;
            for (int y = -r; y < r; ++y) {
                if (x2 + y*y < r2) {
                    Point point = new Point(x, y);
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
            Point pa = ps[a];
            for (int b = a+1; b < pc; ++b) {
                Point pb = ps[b];

                for (int x = -r+1; x < r; ++x) {
                    if (x == 0) {
                        if (pa.x < 0 && pb.x > 0 || pa.x > 0 && pb.x < 0) {
                            res += r - 1;
                        }
                    } else {
                        PointD va = vi[a][x + r];
                        PointD vb = vi[b][x + r];

                        if (va != null && vb != null) {
                            res += between(va, vb);
                        } else if (va != null || vb != null) {
                            PointD cr = ci[x + r]; // +

                        }
                    }
                }
            }
        }
        System.out.println(res);
        System.out.println(res/3);
    }

    private long between(PointD va, PointD vb) {
        double mny = Math.min(va.y, vb.y);
        double mxy = Math.max(va.y, vb.y);

        int mni = (int) mny;
        int mxi = (int) mxy;

        if (mxi < 0 || mxi == mxy) {
            --mxi;
        }

        if (mni > 0 || mni == mny) {
            ++mni;
        }

        return Math.max(0, mxi - mni + 1);
    }

    private PointD findVertIntersection(Point p, int vx) {
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

        // y = py/px * x;
        // x^2 + y^2 = r^2
        // x^2 * (px^2 + py^2)/px^2 = r^2
        // x = +- r * px * sqrt( 1/(px^2 + py^2) );
        // y = +- r * py * sqrt( 1/(px^2 + py^2) );

        double sq = Math.sqrt(p.x*p.x + p.y*p.y);
        double x = -r * p.x / sq;
        double y = -r * p.y / sq;

        if (x > 0 && vx >= x) {
            return null;
        }

        if (x < 0 && vx <= x) {
            return null;
        }

        double vy = p.y * vx / (double)p.x;

        return new PointD(vx, vy);
    }
}
