package tasks;

import utils.OtherUtils;
import utils.log.Logger;

import static java.lang.Math.max;

//Answer :
public class Task_339 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_339());
        Logger.close();
    }

    public void solving() {
        OtherUtils.deepFillDouble(p, -1);
        System.out.println(OtherUtils.formatDouble(find(n, n, 0), 6));
    }

    int maxdeep = 100;
    public int n = 5;
    double p[][][] = new double[2*n+1][2*n+1][maxdeep + 1];
    private double find(int w, int b, int deep) {
        if (deep > maxdeep) {
            return b;
        }

        if (b == 0) {
            return 0;
        }

        if (w <= 0) {
            return b;
        }

        if (p[w][b][deep] != -1) {
            return p[w][b][deep];
        }

        double all = w + b;
        double wb = 0;
        double bb = 0;
        for (int i = 0; i <= w+1; ++i) {
            wb = max(wb, find(w + 1 - i, b - 1, deep+1));
            bb = max(bb, find(w - 1 - i, b + 1, deep+1));
        }

        newentry();

        return p[w][b][deep] = w/all * wb + b/all * bb;
    }

    int cnt = 0;
    private void newentry() {
        ++cnt;
        if (cnt % 100 == 0) {
            System.out.println(cnt);
        }
    }
}
