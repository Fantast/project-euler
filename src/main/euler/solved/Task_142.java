package solved;

import tasks.ITask;
import tasks.Tester;

import static utils.MyMath.isExactSquare;

//Answer : 1006193
public class Task_142 implements ITask {
    public void solving() {
        TOP:
        for (long a = 900; ; ++a) {
            long a2 = a * a;
            for (long c = 2; c < a; ++c) {
                long c2 = c * c;
                for (long d = 1; d < c; ++d) {
                    long d2 = d * d;

                    if ((c2+d2)%2 != 0) continue;

                    long b2 = c2 + d2 - a2;

                    if (b2>=d2 || (a2+b2)%2 != 0) continue;

                    if (!isExactSquare(b2)) continue;

                    long e2 = a2 - d2;
                    if (e2 >= c2) continue;
                    if (!isExactSquare(e2)) continue;

                    long f2 = e2+d2-c2;
                    if (f2>=d2 || (f2+e2)%2 != 0) continue;
                    if (!isExactSquare(f2)) continue;

                    long x = (a2+b2)/2;
                    long y = (e2+f2)/2;
                    long z = (c2-d2)/2;

                    System.out.println("Found : " + x + " " + y + " " + z);
                    System.out.println("   Sum: " + (x+y+z));
                    break TOP;
                }
            }
        }
    }

    public static void main(String[] args) {
        Tester.test(new Task_142());
    }
}