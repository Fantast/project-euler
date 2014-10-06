package tasks;

import utils.Point;
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
    int pc = 0;
    public void solving() {
        for (int x = -r; x < r; ++x) {
            int x2 = x*x;
            for (int y = -r; y < r; ++y) {
                if (x2 + y*y < r2) {
                    ps[pc++] = new Point(x, y);
                }
            }
        }
        System.out.println(pc);

        long res = 0;
        for (int a = 1; a < pc; ++a) {
            for (int b = 1; b < pc; ++b) {
                if (a == b) {
                    ps[a] = ps[b];
                }
            }
        }
        System.out.println(res);
    }
}
