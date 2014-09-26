package solved;

import tasks.ITask;
import tasks.Tester;

import static java.lang.Math.PI;
import static java.lang.Math.sqrt;

//Answer : 0.00396087
public class Task_199 implements ITask {
    double R = 1;
    int steps = 10;
    Triple t1[] = new Triple[2000000];
    Triple t2[] = new Triple[2000000];

    public void solving() {
        double r0 = sqrt(3 * R * R) * 2 - 3 * R;
        double s = area(R) - 3 * area(r0);

        int cnt = 4;
        t1[0] = t1[1] = t1[2] = new Triple(r0, r0, R);
        t1[3] = new Triple(r0, r0, r0);
        for (int i = 0; i < steps; ++i) {
            Triple curr[] = (i % 2 == 0) ? t1 : t2;
            Triple next[] = (i % 2 == 1) ? t1 : t2;

            int ncnt = 0;
            for (int j = 0; j < cnt; ++j) {
                Triple t = curr[j];
                double nr = inscribed(t);

                s -= area(nr);

                next[ncnt++] = new Triple(t.r1, t.r2, nr);
                next[ncnt++] = new Triple(t.r1, t.r3, nr);
                next[ncnt++] = new Triple(t.r3, t.r2, nr);
            }

            cnt = ncnt;
        }

        System.out.println(String.format("%.8f", s / area(R)));
    }

    private double area(double r) {
        return PI * r * r;
    }

    private double inscribed(Triple t) {
        double r1 = t.r1;
        double r2 = t.r2;
        double r3 = t.r3;

        if (r3 == R) {
            return r1*r2*r3 / (  r1*r3 + r2*r3 - r1*r2 + 2*sqrt(r1*r2*r3*(r3-r1-r2)) );
        } else {
            return r1*r2*r3 / (  r1*r3 + r2*r3 + r1*r2 + 2*sqrt(r1*r2*r3*(r3+r1+r2)) );
        }
    }

    class Triple {
        double r1, r2, r3;

        public Triple(double r1, double r2, double r3) {
            if (r1 > r3) {
                double tmp = r3;
                r3 = r1;
                r1 = tmp;
            }
            if (r2 > r3) {
                double tmp = r3;
                r3 = r2;
                r2 = tmp;
            }

            this.r1 = r1;
            this.r2 = r2;
            this.r3 = r3;
        }
    }

    public static void main(String[] args) {
        Tester.test(new Task_199());
    }
}
