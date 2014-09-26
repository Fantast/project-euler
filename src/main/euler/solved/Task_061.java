package solved;

import tasks.ITask;
import tasks.Tester;
import utils.log.Logger;

//Answer : 28684
public class Task_061 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_061());
        Logger.close();
    }

    int len[] = new int[9];
    int p[][] = new int[9][10000];
    boolean used[] = new boolean[9];

    public void solving() {
        long res = 0;

        for (int i = 3; i <= 8; ++i) {
            int ln = 0;
            for (int n = 1; ; ++n) {
                int pn = n * (2 + (i-2)*(n-1)) /2;
                if (pn >= 10000) break;
                if (pn < 1000) continue;

                p[i][ln++] = pn;
            }
            len[i] = ln;
        }

        used[3] = true;
        for (int i = 0; i < len[3]; ++i) {
            numbers[0] = p[3][i];
            if (find(1)) break;
        }

        for (int i = 0; i < 6; ++i) {
            System.out.println(numbers[i]);
            res += numbers[i];
        }

        System.out.println( "Sum: " + res );
    }

    int numbers[] = new int[6];
    private boolean find(int ind) {
        if (ind == 6) {
            return numbers[5] % 100 == numbers[0] / 100;
        }

        int end = numbers[ind-1]%100;
        for (int i = 3; i < 9; ++i) {
            if (!used[i]) {
                used[i] = true;
                for (int j = 0; j < len[i]; ++j) {
                    if (p[i][j]/100 == end) {
                        numbers[ind] = p[i][j];
                        if ( find(ind+1) ) return true;
                    }
                }
                used[i] = false;
            }
        }

        return false;
    }
}
