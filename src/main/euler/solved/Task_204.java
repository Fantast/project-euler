package solved;

import tasks.ITask;
import tasks.Tester;
import utils.log.Logger;

import static utils.MyMath.getPrimesBetween;

//Answer : 2944730
public class Task_204 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_204());
        Logger.close();
    }

    private static int LIMIT = 1000000000;
    private static int MAX = 100;

    boolean isHumming[] = new boolean[LIMIT+1];

    public void solving() {
        long primes[] = getPrimesBetween(0, MAX);

        int cnt = 0;
        isHumming[1] = true;
        for (int i = 1; i <= LIMIT; ++i) {
            if (!isHumming[i]) {
                cnt++;
            } else {
                for (long p : primes) {
                    long hn = i*p;
                    if (hn <= LIMIT) {
                        isHumming[(int)hn] = true;
                    } else {
                        break;
                    }
                }
            }
        }

        System.out.println( LIMIT - cnt );
    }
}
