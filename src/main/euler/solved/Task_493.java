package solved;

import tasks.AbstractTask;
import tasks.Tester;
import utils.log.Logger;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.ROUND_HALF_UP;

//Answer : 6.818741802
public class Task_493 extends AbstractTask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_493());
        Logger.close();
    }

    private MathContext mc = new MathContext(1000, RoundingMode.HALF_UP);

    public void solving() {
        BigDecimal r = ONE;
        for (int i = 41; i <= 60; ++i) {
            BigDecimal a = bd(i);
            BigDecimal b = bd(i+10);
            r = r.multiply(a.divide(b, mc));
        }
        r = ONE.subtract(r).multiply(bd(7)).setScale(9, ROUND_HALF_UP);
        System.out.println(r);
    }

    private BigDecimal bd(int i) {
        return new BigDecimal(i);
    }
}
