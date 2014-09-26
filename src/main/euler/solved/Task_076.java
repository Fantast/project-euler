package solved;

import tasks.ITask;
import tasks.Tester;
import utils.log.Logger;

//Answer : 190569291
public class Task_076 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_076());
        Logger.close();
    }

    final int n = 20;
    public void solving() {
        System.out.println( get(1, 0)-1 );
    }

    long d[][] = new long[200][200];
    private long get(int beg, int sum) {
        if (sum == n) return 1;
        if (d[beg][sum] != 0) return d[beg][sum];

        long res = 0;
        for (int i = beg; i <= (n-sum); ++i) {
            res += get(i, sum+i);
        }

        return d[beg][sum] = res;
    }
}
