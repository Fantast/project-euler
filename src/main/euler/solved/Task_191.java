package solved;

import tasks.ITask;
import tasks.Tester;
import utils.log.Logger;

//Answer : 1918080160
public class Task_191 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_191());
        Logger.close();
    }

    public void solving() {
        int nd = 30;
        //                [days][L count][A count]
        long ps[][][] = new long[nd+1][2][4];

        ps[0][0][0] = 1;
        for (int i = 1; i <= nd; ++i) {
            for (int j = 1; j <= 2; ++j) {
                ps[i][0][j] = ps[i-1][0][j-1];
                ps[i][1][j] = ps[i-1][1][j-1];
            }

            ps[i][0][0] = ps[i-1][0][0] + ps[i-1][0][1] + ps[i-1][0][2];
            ps[i][1][0] = ps[i-1][1][0] + ps[i-1][1][1] + ps[i-1][1][2]
                        + ps[i-1][0][0] + ps[i-1][0][1] + ps[i-1][0][2];
        }

        long res = ps[nd][0][0] + ps[nd][0][1] + ps[nd][0][2]
                 + ps[nd][1][0] + ps[nd][1][1] + ps[nd][1][2];

        System.out.println(res);
    }
}