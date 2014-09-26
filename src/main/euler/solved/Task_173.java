package solved;

import tasks.ITask;
import tasks.Tester;
import utils.log.Logger;

//Answer : 1572729
public class Task_173 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_173());
        Logger.close();
    }

    final static int GIVEN = 1000000;
    public void solving() {
        long res = 0;
//        long mx = (long) sqrt(GIVEN);

        for (int i = 3; ; ++i) {
            if ( (4*i-4) > GIVEN) break;
            for (int j = i-2; j>0 && (i*i - j*j <= GIVEN); j-=2) {
                ++res;
            }
        }

        System.out.println( res );
    }
}