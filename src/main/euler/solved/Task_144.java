package solved;

import tasks.ITask;
import tasks.Tester;

import static java.lang.Math.abs;
import static java.lang.Math.sqrt;

//Answer : 354
public class Task_144 implements ITask {
    public void solving() {
        long res = 0;

        double x0 = 0;
        double y0 = 10.1;
        double x1 = 1.4;
        double y1 = -9.6;

        while (!(abs(x1) < 0.01 && y1>0)) {
            ++res;

            double x2, y2;
            if (x1 == 0) {
                x2 = -x0;
                y2 = y0;
            } else {
                double ax = x1 - x0;
                double ay = y1 - y0;

                double nx = 1;
                double ny = y1/(4*x1);

                double cf = 2*(ax*nx + ay*ny)/(nx*nx + ny*ny);

                double bx = cf * nx - ax;
                double by = cf * ny - ay;

                double a = by / bx;
                double b = -by/bx*x1 + y1;

                double A = 4+a*a;
                double B = a*b;
                double C = b*b-100;

                double D = sqrt(B*B - A*C);

                x2 = (-B - D) / A;
                y2 = a*x2 + b;

                if (equal(x2, x1) && equal(y1, y1)) {
                    x2 = (-B + D) / A;
                    y2 = a*x2 + b;
                }
            }

            x0 = x1;
            y0 = y1;
            x1 = x2;
            y1 = y2;
        }

        System.out.println(res);
    }

    double EPS = 1e-10;
    private boolean equal(double a, double b) {
        return abs(a-b) < EPS;
    }

    public static void main(String[] args) {
        Tester.test(new Task_144());
    }
}
