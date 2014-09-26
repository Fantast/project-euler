package solved;

import tasks.ITask;
import tasks.Tester;
import utils.log.Logger;

//Answer : 333082500
public class Task_120 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_120());
        Logger.close();
    }

    public void solving() {
        long res = 0;

        for (int i = 3; i <= 1000; ++i) {
            res += getMaxRem(i);
        }
        System.out.println( res );
    }

    private long getMaxRem(int a) {
        return a*  ((a+1)/2-1)*2;
    }
}
