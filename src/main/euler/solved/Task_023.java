package solved;

import tasks.ITask;
import tasks.Tester;
import utils.log.Logger;

import static utils.MyMath.getDivisors;

//Answer : 4179871
public class Task_023 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_023());
        Logger.close();
    }

    final static int MAX_NUM = 28123;
    boolean can[] = new boolean[MAX_NUM+1];
    int abunds[] = new int[7000];
    int n = 0;
    public void solving() {
        int res = 0;

        for (int i = 2; i <= MAX_NUM; ++i) {
            int s = -i;
            for (long div : getDivisors(i)) {
                s += div;
            }
            if (s > i) abunds[n++] = i;
        }


        for (int i = 0; i < n; ++i) {
            for (int j = i, k; (k=abunds[i]+abunds[j])<=MAX_NUM && j < n; ++j) {
                can[k] = true;
            }
        }

        for (int i = 1; i <= MAX_NUM; ++i) {
            if (!can[i]) res += i;
        }

        System.out.println(res);
    }
}
