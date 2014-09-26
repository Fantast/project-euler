package opener;

import tasks.ITask;
import tasks.Tester;
import utils.log.Logger;

//Answer :
public class Task_10 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_10());
        Logger.close();
    }

    public void solving() {
        System.out.println(0xC0DE);


    }
}
