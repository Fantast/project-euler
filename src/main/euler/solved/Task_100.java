package solved;

import tasks.ITask;

import static java.lang.Math.max;
import static java.lang.Math.sqrt;
import static utils.MyMath.isExactSquare;

//Answer : 756872327473
public class Task_100 implements ITask {
    public void solving() {
        final long LIM = 1000000000000L;
        long a, b;
        for (b = 20; ; ++b) {
            long n1 = 2*b*b - 1;
            if ( !isExactSquare(n1)) n1 += 2;
            if ( isExactSquare(n1)) {
                a = (long)sqrt(n1) + b;
                long A = max(a*a - b*b, 2*a*b);
                if (A > LIM) {
                    break;
                }
            }
        }

        long A = max(a*a - b*b, 2*a*b);
        long x = (1 + a*a + b*b) / 2;
        System.out.println("All: " + A );
        System.out.println("Blue: " + x );
    }
}
