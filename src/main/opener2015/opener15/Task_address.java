package opener15;

import tasks.ITask;
import tasks.Tester;
import utils.MyMath;
import utils.log.Logger;

//Answer :
public class Task_address implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_address());
        Logger.close();
    }

    public void solving() {
        long f15 = 1307674368000L;
        System.out.println(MyMath.getDivisorsCount(f15)/2 - 1);
    }
}
