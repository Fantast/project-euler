package opener15;

import tasks.ITask;
import tasks.Tester;
import utils.log.Logger;

//Answer :
public class Task_ implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_());
        Logger.close();
    }

    public void solving() {


    }
}
