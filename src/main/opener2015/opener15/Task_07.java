package opener15;

import tasks.ITask;
import tasks.Tester;
import utils.log.Logger;

//Answer : 0.5483113556160754788241383888820083964063166337355994
public class Task_07 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_07());
        Logger.close();
    }

    public void solving() {
        //just pi^2 / 18, because the only solutions x=y=z
    }
}
