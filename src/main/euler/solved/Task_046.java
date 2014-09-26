package solved;

import tasks.ITask;
import tasks.Tester;
import utils.log.Logger;

import static utils.MyMath.isPrime;

//Answer : 5777
public class Task_046 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_046());
        Logger.close();
    }

    public void solving() {
        long i;
        for (i = 3; ; i+=2) {
            if (isPrime(i)) {
                System.out.println(" "+i+" - prime!");
                continue;
            }
            boolean found = false;
            long lim = (int) Math.sqrt((i - 1)/2) + 1;
            for (long j = 1; j <= lim; ++j) {
                long p = i - j*j*2;
                if (p < 0) break;
                if ( isPrime(p) ) {
                    found = true;
                    System.out.println("" + i+ " = "+ p +" + 2*" + j+"*"+j);
                    break;
                }
            }
            if (!found) break;
        }

        System.out.println(i);
    }
}
