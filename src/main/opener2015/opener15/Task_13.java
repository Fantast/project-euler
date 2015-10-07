package opener15;

import tasks.ITask;
import tasks.Tester;
import utils.MyMath;
import utils.log.Logger;

//Answer :
public class Task_13 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_13());
        Logger.close();
    }

    long OP = 673637;
    public void solving() {
        long res = 0;
        for (long n = 2016; n < OP; ++n) {
            res = (res + pw(n)) % OP;
        }
        System.out.println(res);
    }

    private long pw(long n) {
        return MyMath.modPow(n, OP, OP);
    }
}
