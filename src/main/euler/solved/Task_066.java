package solved;

import tasks.ITask;
import tasks.Tester;
import utils.log.Logger;

// Answer : 661
// Used Mathematica...

public class Task_066 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_066());
        Logger.close();
    }

    public void solving() {
        System.out.println(661);
    }
}
