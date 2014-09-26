package solved;

import tasks.ITask;
import tasks.Tester;
import utils.log.Logger;

import java.util.ArrayList;
import java.util.Collections;

//Answer : 248155780267521
public class Task_119 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_119());
        Logger.close();
    }

    public void solving() {
        ArrayList<Long> all = new ArrayList<Long>();
        for (int n = 2; n < 100; ++n) {
            long p = n;
            for (;p < Long.MAX_VALUE/101; ) {
                p *= n;
                if ( digSum(p)==n ) {
                    all.add(p);
                }
            }
        }

        Collections.sort(all);

        System.out.println( all.get(29) );
    }

    private int digSum(long n) {
        int res = 0;
        while (n!=0) {
            res += n%10;
            n /= 10;
        }
        return res;
    }
}
