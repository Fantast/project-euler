package solved;

import tasks.ITask;
import tasks.Tester;
import utils.log.Logger;

import static utils.MyMath.gcd;

//Answer : 1209002624
public class Task_202 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_202());
        Logger.close();
    }

    long N = 12017639147L;

    public void solving() {
        long res = 0;
        for (long k = 0; k < N; k += 12) {
            if (k%12000000 == 0) {
                System.out.println("Progress: " + k/12);
            }
            long m1 = N - k + 3;
            if (m1 % 4 == 0) {
                long s1 = m1/4;
                long s2 = k/2 + m1/4;

                if (gcd(s1, s2) == 1) {
                    ++res;
                }
            }

            long m2 = N - k - 3;
            if (m2%4==0) {
                long s1 = m2/4;
                long s2 = k/2 + m2/4 + 3;


                if (gcd(s1, s2) == 1) {
                    ++res;
                }
            }
        }

        System.out.println(res*2);
    }
}
