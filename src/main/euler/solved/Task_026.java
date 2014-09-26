package solved;

import tasks.ITask;
import tasks.Tester;
import utils.log.Logger;

import java.util.Arrays;

//Answer : 983
public class Task_026 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_026());
        Logger.close();
    }

    public void solving() {
        int res = -1;
        int resd = 7;

        for (int i = 1; i < 1000; ++i) {
            int r = getCycle(i);
            if (r > res) {
                resd = i;
                res = r;
            }
        }
        System.out.println("For number: " + resd);
        System.out.println("Cycle length: " + res);
    }

    int remPos[] = new int[1001];
    private int getCycle(int n) {
        Arrays.fill(remPos, 0, n, -1);
        for (int i = 0, k = 1; ; ++i) {
            if (k == 0) return 0;
            k %= n;
            if (remPos[k] != -1) return i-remPos[k];
            remPos[k] = i;
            k *= 10;
        }
    }
}
