package solved;

import tasks.ITask;
import tasks.Tester;

import static java.lang.Math.sqrt;
import static utils.MyMath.isExactSquare;

//Answer : 518408346
public class Task_094 implements ITask {

    public void solving() {
        System.out.println("Start!");
        long sum = 0;
        for (long p = 5; p <= 1000000000; p += 2) {
            if (check(p, -1)) {
                sum += p;
            }
            if (check(p, +1)) {
                sum += p;
            }
        }
        System.out.println(sum);
    }

    private boolean check(long p, int d) {
        long pn = p + d;

        if (pn % 3 != 0) {
            return false;
        }
        long a = pn / 3;
        long b = a - d;

        long s = 4 * a * a - b * b;
        if (s <= 0 || !isExactSquare(s)) {
            return false;
        }

        s = (long) sqrt(s);

        return (b * s) % 4 == 0;
    }

    public static void main(String[] args) {
        Tester.test(new Task_094());
    }
}
