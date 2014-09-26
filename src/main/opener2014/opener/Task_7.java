package opener;

import tasks.ITask;
import tasks.Tester;
import utils.MyMath;
import utils.log.Logger;

import java.math.BigInteger;

import static java.math.BigInteger.valueOf;
import static utils.MyMath.lcm;

//Answer :
public class Task_7 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_7());
        Logger.close();
    }

    public void solving() {
        BigInteger res = valueOf(1);
        for (int a = 2; a <= 666; ++a) {
            for (int b = a; b <= 666; ++b) {
                if (can(a, b)) {
                    System.out.println(a + " " + b);
                    res = lcm(res, valueOf(a + a + b + b));
                }
            }
        }
        System.out.println(res);
    }

    private boolean can(int a, int b) {
        if (a == 3 && b % 2==0) {
            return true;
        }
        return a != 3 && a*b%3 == 0;
    }
}
