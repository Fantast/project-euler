package solved;

import tasks.ITask;
import utils.FileUtils;

import java.io.IOException;
import java.util.ArrayList;

import static java.lang.Integer.parseInt;

//Answer : 259679
public class Task_107 implements ITask {
    int n;
    int a[][];
    private static final int INF = Integer.MAX_VALUE / 5;

    public void solving() throws IOException {
        ArrayList<String> lines = FileUtils.readLines("files/network.txt");
        n = lines.size();
        a = new int[n][n];
        int begin = 0;
        for (int i = 0; i < n; ++i) {
            String spl[] = lines.get(i).split(",");
            for (int j = 0; j < n; ++j) {
                a[i][j] = spl[j].equals("-") ? INF : parseInt(spl[j]);
                if (a[i][j] != INF) begin += a[i][j];
            }
        }
        begin /= 2;

        long res = 0;
        boolean used[] = new boolean[n];
        used[0] = true;
        for (int i = 1; i < n; ++i) {
            int ind = -1;
            int r = INF;
            for (int j = 0; j < n; ++j) {
                if (!used[j]) continue;
                for (int k = 0; k < n; ++k) {
                    if (used[k]) continue;
                    if (a[j][k] < r) {
                        r = a[j][k];
                        ind = k;
                    }
                }
            }
            used[ind] = true;
            res += r;
        }

        System.out.println(begin - res);
    }
}
