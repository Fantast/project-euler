package solved;

import tasks.ITask;
import tasks.Tester;
import utils.log.Logger;

import static utils.MyMath.getPrimeDivisors;

//Answer : 134043
public class Task_047 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_047());
        Logger.close();
    }

    final int NEED = 4;
    int curr = 0;

    public void solving() {
        int i = 1;
        for (;; ++i) {
            if ( isOk(i) ) {
                ++curr;
                if (curr == NEED) break;
            } else {
                curr = 0;
            }
        }

        System.out.println( i - 3 );
    }

    private boolean isOk(int n) {
        return getPrimeDivisors(n).size() == NEED;
    }
}
