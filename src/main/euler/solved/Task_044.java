package solved;

import tasks.ITask;
import tasks.Tester;
import utils.MyMath;
import utils.log.Logger;

//Answer : 5482660
public class Task_044 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_044());
        Logger.close();
    }

    final int MAX = 2000;
    public void solving() {

        int p[] = new int[MAX+1];
        for (int i = 1; i <= MAX; ++i) {
            p[i] = i*(3*i-1)/2;
        }

        for (int i = 1; i <= MAX; ++i) {
            for (int j = 1; j <= MAX; ++j) {
                if ( isPentagonal(p[i] + p[j])  && isPentagonal(p[i]+2*p[j])) {
                    System.out.println(i);
                    System.out.println(p[i]);
                    return;
                }
            }
        }
    }

    private boolean isPentagonal(int p) {
        long d = 1 + 24*p;

        if ( !MyMath.isExactSquare(d) ) return false;

        long ds = (long) Math.sqrt(d);
        return (1 + ds) % 6 == 0;
    }
}
