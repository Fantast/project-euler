package solved;

import tasks.ITask;
import tasks.Tester;
import utils.log.Logger;

//Answer : 1587000
public class Task_112 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_112());
        Logger.close();
    }

    public void solving() {
        int res = 0;
        
        int i;
        for (i = 100; ; ++i) {
            if ( isBouncy(i) ) {
                ++res;
                if (res*100 == 99*i) break;
            }
        }

        System.out.println(i);
    }

    private boolean isBouncy(int n) {
        int wasD = n%10;
        n /= 10;

        boolean isIncr = false;
        boolean isDecr = false;
        while (n != 0) {
            int d = n%10;
            n /= 10;

            if (d > wasD) isIncr = true;
            if (d < wasD) isDecr = true;

            if (isIncr && isDecr) return true;

            wasD = d;
        }

        return false;
    }
}
