package opener;

import tasks.ITask;
import tasks.Tester;
import utils.MyMath;
import utils.log.Logger;

//Answer :
public class Task_23 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_23());
        Logger.close();
    }

    public void solving() {
        long res = 1;
        for (long i = 15; i <= 43; i+=2) {
            res = MyMath.lcm(res, i);
        }
        System.out.println(res);


    }
}
