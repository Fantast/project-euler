package solved;

import tasks.AbstractTask;
import tasks.Tester;
import utils.OtherUtils;
import utils.log.Logger;

//Answer : 83735848679360680
public class Task_181 extends AbstractTask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_181());
        Logger.close();
    }

    int b = 60;
    int w = 40;
    boolean debug = false;
//    int b = 3;
//    int w = 3;
//    boolean debug = true;

    public void solving() {
        OtherUtils.deepFillLong(cnt, -1);
        System.out.println(count(0, 0, b, w));
    }

    int gc = 0;
    int gb[] = new int[1000];
    int gw[] = new int[1000];

    long cnt[][][][] = new long[101][41][101][41];

    private long count(int mnb, int mnw, int b, int w) {
        if (b == 0 && w == 0) {
            if (debug) {
                print();
            }
            return 1;
        }

        if (b < mnb) {
            return 0;
        }

        if (cnt[mnb][mnw][b][w] != -1) {
            return cnt[mnb][mnw][b][w];
        }

        long res = 0;

        for (int i = mnb==0&&mnw==0?1:mnw; i <= w; ++i) {
            gb[gc] = mnb;
            gw[gc++] = i;
            res += count(mnb, i, b - mnb, w - i);
            --gc;
        }

        for (int i = mnb + 1; i <= b; ++i) {
            for (int j = 0; j <= w; ++j) {
                gb[gc] = i;
                gw[gc++] = j;
                res += count(i, j, b - i, w - j);
                --gc;
            }
        }

        if (!debug) {
            return cnt[mnb][mnw][b][w] = res;
        }
        return res;
    }

    private void print() {
        for (int i = 0; i < gc; ++i) {
            for (int j = 0; j < gb[i]; ++j) {
                System.out.print("B");
            }
            for (int j = 0; j < gw[i]; ++j) {
                System.out.print("W");
            }
            System.out.print("  ");
        }
        System.out.println("  ");
    }
}
