package solved;

import tasks.AbstractTask;
import tasks.Tester;
import utils.log.Logger;

//Answer : 810834388
public class Task_246 extends AbstractTask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_246());
        Logger.close();
    }

    public void solving() {
        //done in geogebra - the locus is ellipse-like figure , bounded by this rectangle
        long res = 0;
        for (int x = -15500; x <= 15500; ++x) {
            progress100(x);
            for (int y = -19000; y <= 19000; ++y) {
                if (legit(x, y)) {
                    ++res;
                }
            }
        }
        System.out.println(res);
    }

    private boolean legit(long xp, long yp) {
        if (onEllipse(xp, yp)) {
            return false;
        }

        // http://www.wolframalpha.com/input/?i=solve+x%5E2%2F7500%5E2+%2B+%28m*x+-+m*a+%2B+b%29%5E2%2F%282500%5E2*5%29+%3D+1+for+x
        // http://www.wolframalpha.com/input/?i=solve+-x%5E2*m%5E2%2B2*x*y*m-y%5E2%2B56250000*m%5E2%2B31250000+for+m

        double x1;
        double y1;

        double m2;
        double x2;
        double y2;

        if (xp == 7500) {
            x1 = 7500;
            y1 = 0;

            m2 = (yp*yp - 31250000.0)/(15000.0 * yp);
        } else if (xp==-7500) {
            x1 = -7500;
            y1 = 0;

            m2 = (31250000.0 - yp*yp)/(15000.0 * yp);
        } else {
            double xy = xp*yp;
            double t = xp*xp - 56250000;
            double d = 2500*Math.sqrt(5*xp*xp + 9*yp*yp - 281250000);

            double m1 = (xy + d)/t;
            m2 = (xy - d)/t;

            x1 = x(xp, yp, m1);
            y1 = y(x1, xp, yp, m1);
        }
        x2 = x(xp, yp, m2);
        y2 = y(x2, xp, yp, m2);

        double ax = x1 - xp;
        double ay = y1 - yp;
        double bx = x2 - xp;
        double by = y2 - yp;

        double dotProduct = ax*bx + ay*by;
        double cosa = dotProduct/Math.sqrt(ax*ax + ay*ay)/Math.sqrt(bx*bx + by*by);

        return cosa < cos45;
    }

    private double x(double xp, double yp, double m) {
        double m2 = m*m;
        return 9*(xp*m2-yp*m)/(9*m2+5);
    }

    private double y(double x, double xp, double yp, double m) {
        return m*x - m*xp + yp;
    }

    long n25 = 2500*2500*5;
    long n75 = 7500*7500;
    long n7525 = n75*n25;
    double cos45 = Math.cos(Math.PI/4);

    private boolean onEllipse(long x, long y) {
        return x*x*n25 + y*y*n75 <= n7525;
    }
}
