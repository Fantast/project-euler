package solved;

import tasks.ITask;
import tasks.Tester;
import utils.log.Logger;

import static utils.MyMath.phi;

//Answer : 303963552391
public class Task_072 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_072());
        Logger.close();
    }

    public void solving() {
        long count = 1000000;
        long res = 0;

        for (int i = 2; i <= count; ++i) {
            res += phi(i);
        }

        System.out.println(res);
    }
}
