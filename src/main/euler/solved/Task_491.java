package solved;

import tasks.AbstractTask;
import tasks.Tester;
import utils.MyMath;
import utils.log.Logger;

//Answer : 194505988824000
public class Task_491 extends AbstractTask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_491());
        Logger.close();
    }

    @SuppressWarnings("PointlessArithmeticExpression")
    int SUM = (0 + 9) * 10 / 2 * 2;
    long factorial[] = MyMath.factorials(11);

    public void solving() {
        take(0, 0, 0);
        System.out.println(res);
    }

    int digs[] = new int[10];
    long res = 0;

    private void take(int d, int count, int sum) {
        if (count == 10) {
            if (abs(sum - (SUM - sum)) % 11 == 0) {
                res += count();
            }
            return;
        }
        if (d == 10) {
            return;
        }

        for (int dc = 0; dc <= 2; ++dc) {
            digs[d] = dc;
            take(d + 1, count + dc, sum + dc * d);
        }
        digs[d] = 0;
    }

    private long count() {
        long res1 = factorial[10];
        long res2 = factorial[10];
        for (int di = 0; di < 10; ++di) {
            res1 /= factorial[digs[di]];
            res2 /= factorial[2-digs[di]];
        }

        //but actually res1 == res2

        if (digs[0] != 0) {
            long rz1 = factorial[9];
            for (int di = 1; di < 10; ++di) {
                rz1 /= factorial[digs[di]];
            }
            res1 -= rz1;
        }

        return res1 * res2;
    }
}
