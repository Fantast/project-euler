package solved;

import tasks.AbstractTask;
import tasks.Tester;
import utils.log.Logger;

import java.util.PriorityQueue;

import static java.lang.Math.sqrt;

//Answer : 782252
public class Task_247 extends AbstractTask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_247());
        Logger.close();
    }

    int possCurve = 0;

    double maxSize = 0;
    int maxn = -1;
    int currn = 0;

    int lim = 3;

    PriorityQueue<Curve> q = new PriorityQueue<>();

    public void solving() {
        push(new Curve(1, 0, 0, 0));

        while (possCurve != 0) {
            Curve curve = poll();

            Curve top = curve.top();
            Curve right = curve.right();

            push(top);
            push(right);
        }
        System.out.println(maxn);
    }

    private void push(Curve curve) {
        if (curve.left <= lim && curve.bottom <= lim) {
            possCurve++;
        }
        q.add(curve);
    }

    private Curve poll() {
        Curve curve = q.poll();
        ++currn;

        if (curve.left == lim && curve.bottom == lim) {
            maxSize = curve.sqSize;
            maxn = currn;
        }

        if (curve.left <= lim && curve.bottom <= lim) {
            possCurve--;
        }

        return curve;
    }

    static class Curve implements Comparable<Curve> {
        double x;
        double y;
        double sqSize;
        int left;
        int bottom;

        public Curve(double x, double y, int left, int bottom) {
            this.x = x;
            this.y = y;
            this.left = left;
            this.bottom = bottom;

            sqSize = 0.5 * (sqrt((x - y) * (x - y) + 4) - x - y);
        }

        public Curve right() {
            return new Curve(x + sqSize, y, left + 1, bottom);
        }

        public Curve top() {
            return new Curve(x, y + sqSize, left, bottom + 1);
        }

        @Override
        public int compareTo(Curve o) {
            return Double.compare(o.sqSize, sqSize);
        }

        public String toString() {
            return "l: " + left + "; b: " + bottom;
        }
    }
}
