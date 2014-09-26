package solved;

import tasks.ITask;
import tasks.Tester;
import utils.log.Logger;

import static java.lang.Math.abs;

//Answer : 2772
public class Task_085 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_085());
        Logger.close();
    }

    public void solving() {
        long res = 0;

        long dr = 2000000;
        for (long a = 1; a <= 2000000; ++a) {
            for (long b = 1; b <= 2002000/a; ++b) {
                if (a*b > 3000) continue;
                long f = abs(2000000-a*b*(a+1)*(b+1)/4);
                if (f < dr) {
                    dr = f;
                    res = a*b;
                }
            }
        }

        System.out.println(res);
    }
}
