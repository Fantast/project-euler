package solved;

import tasks.ITask;
import tasks.Tester;
import utils.OtherUtils;
import utils.log.Logger;

//Answer : 2178309
public class Task_301 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_301());
        Logger.close();
    }

    int bits = 30;
    public void solving() {
        OtherUtils.deepFillInt(f, -1);
        System.out.println(find(bits+2, 1));
    }

    int f[][] = new int[35][2];
    private int find(int bits, int beg) {
        if (bits == 1) return 1;
        if (f[bits][beg] == -1) {
            int r = find(bits-1, 1 - beg);
            if (beg == 0) {
                r += find(bits - 1, beg);
            }

            return f[bits][beg] = r;
        }

        return f[bits][beg];
    }
}
