package solved;

import tasks.ITask;
import tasks.Tester;
import utils.OtherUtils;
import utils.log.Logger;

import java.math.BigDecimal;
import java.math.MathContext;

//Answer : 52.6494571953
public class Task_286 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_286());
        Logger.close();
    }

    public void solving() {
        double ql = 50;
        double qr = 100;
        BigDecimal need = val(0.02);

        while (qr - ql >= 1e-12) {
            double qm = (qr + ql) / 2;

            if (getExpected(qm).compareTo(need) > 0) {
                ql = qm;
            } else {
                qr = qm;
            }
        }

        System.out.printf(OtherUtils.formatDouble(qr, 10));
    }

    private static MathContext mc = MathContext.DECIMAL128;

    BigDecimal exp[][] = new BigDecimal[51][21];

    private BigDecimal getExpected(double q) {
        OtherUtils.deepFillObject(exp, val(0));

        BigDecimal Q = val(q);

        exp[0][0] = val(1);
        for (int i = 1; i <= 50; ++i) {
            for (int j = 0; j <= 20; ++j) {
                exp[i][j] = exp[i - 1][j].multiply(val(i).divide(Q, mc), mc);
                if (j != 0) {
                    exp[i][j] = exp[i][j].add(
                            exp[i - 1][j - 1].multiply(val(q - i).divide(Q, mc), mc), mc);
                }
            }
        }

        return exp[50][20];
    }

    private BigDecimal val(double v) {
        return new BigDecimal(v, mc);
    }
}
