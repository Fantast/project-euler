package solved;

import tasks.ITask;
import tasks.Tester;
import utils.log.Logger;

import static utils.OtherUtils.isPandigital;

//Answer : 329468
public class Task_104 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_104());
        Logger.close();
    }

    final static long MOD = 1000000000L;
    final static long BEG_MOD = 1000000000000000L;

    public void solving() {
        long f1 = 1, f2 = 1, f3;
        long n1 = 1, n2 = 1, n3;
        int current = 2;

        while (true) {
            ++current;

            n3 = (n1 + n2)%MOD;
            f3 = f1 + f2;
            if (f3 > BEG_MOD) {
                f2 /= 10;
                f3 /= 10;
            }

            String s3 = "" + f3;
            if (s3.length() > 8 && isPandigital(n3, 9) && isPandigital(s3.substring(0, 9)) ) {
                break;
            }

            if (current%100000 == 0) {
                System.out.println(current);
            }

            f1 = f2; f2 = f3;
            n1 = n2; n2 = n3;
        }

        System.out.println("Answer : " + current);
    }
}
