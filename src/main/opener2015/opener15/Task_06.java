package opener15;

import tasks.ITask;
import tasks.Tester;
import utils.MyMath;
import utils.log.Logger;

//Answer : 150897
public class Task_06 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_06());
        Logger.close();
    }

    public void solving() {
        System.out.println(MyMath.gcd(1056279,905382));
    }
}
