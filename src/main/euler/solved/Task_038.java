package solved;

import tasks.ITask;
import tasks.Tester;
import utils.log.Logger;

import java.util.Arrays;

public class Task_038 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_038());
        Logger.close();
    }

    public void solving() {
        int nRes = 0;
        int res = 0;

        boolean used[] = new boolean[10];

        HERE:
        for (int i = 9000; i <= 9999; ++i) {
            Arrays.fill(used, false);

            String number = "";
            int count = 0;
            for (int j = 1; j < 9; ++j) {
                int n = i*j;
                number += n;

                while (n !=0) {
                    int d = n %10;
                    if (d==0 || used[d]) continue HERE;
                    used[d] = true;
                    n /= 10;
                    ++count;
                }

                if (count == 9) break;
            }

            int t = Integer.valueOf(number);
            if (t > res) {
                res = t;
                nRes = i;
            }
        }

        System.out.println(nRes + " " + res);
    }
}
