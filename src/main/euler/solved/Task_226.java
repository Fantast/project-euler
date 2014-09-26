package solved;

import tasks.ITask;
import tasks.Tester;

import static java.lang.Math.*;

//Answer : 0.11316017
public class Task_226 implements ITask {
    int STEPS = 200;

    double cx = 1/4.0;
    double cy = 1/2.0;
    double cr = 1/4.0;

    public void solving() {
        Segment s = new Segment(0, 0, half, half);

        double currSize = 0.5;
        for (int i = 0; i < STEPS; ++i) {
            currSize /= 2.0;

            double snx = (s.x1 + s.x2)/2;
            double sny = (s.y1 + s.y2)/2 + currSize;

            if (isInCircle(snx, sny)) {
                s = new Segment(s.x1, s.y1, snx, sny);
            } else {
                s = new Segment(snx, sny, s.x2, s.y2);
            }
        }

        double res = 
                blanchArea(s.x2, half) - 
                trapArea(s.x1, s.y1, half, half) +
                circelSegmentArea(s.x1, s.y1, half, half);

        System.out.println(res);
    }

    private boolean isInCircle(double x, double y) {
        return (x - cx)*(x - cx) + (y - cy)*(y - cy) <= cr*cr;
    }

    private double trapArea(double x1, double y1, double x2, double y2) {
        return (y1+y2)*(x2-x1)/2;
    }

    private double circelSegmentArea(double x1, double y1, double x2, double y2) {
        double sd = sqrt( (x2-x1)*(x2-x1) + (y2-y1)*(y2-y1));

        double rd = sqrt( cr*cr - sd*sd/4.0 );

        double a = 2*acos(rd/cr);

        return cr*cr*(a - sin(a))/2.0;
    }

    private double blanchArea(double x1, double x2) {
        return I(x2, 0) - I(x1, 0);
    }

    long MXDEEP = 200;
    double half = 0.5;
    private double I(double x, int deep) {
        if (x>= 1) {
            return half + I(x-1, deep+1);
        }

        if (x > half) {
            return half - I(1-x, deep+1);
        }

        if (deep > MXDEEP) {
            return x*x/2.0;
        }

        return I(2*x, deep+1)/4.0 + x*x/2.0;
    }

    class Segment {
        double x1, y1;
        double x2, y2;
        public Segment(double x1, double y1, double x2, double y2) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }
    }

    public static void main(String[] args) {
        Tester.test(new Task_226());
    }
}
