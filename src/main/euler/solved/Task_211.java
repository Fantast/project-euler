package solved;

import tasks.ITask;
import tasks.Tester;

import static utils.MyMath.isExactSquare;

//Answer : 1922364685
public class Task_211 implements ITask {
    final int LIM = 64000000;

    long res[] = new long[LIM];

    public void solving() {
        long sum = 1;
        for (int i = 2; i < LIM; ++i) {
            long i2 = i*(long)i;
            res[i] += i2+1;

            if (isExactSquare(res[i])) {
                System.out.println(i);
                sum += (long)i;
            }

            for (int j = i+i; j < LIM; j += i) {
                res[j] += i2;
            }
        }
        System.out.println(res[10]);
        System.out.println(sum);
    }

    public static void main(String[] args) {
        Tester.test(new Task_211());
    }
}
