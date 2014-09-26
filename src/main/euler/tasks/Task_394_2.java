package tasks;

import utils.log.Logger;

import java.math.BigDecimal;
import java.math.MathContext;

import static java.math.BigDecimal.ONE;

//Answer :
public class Task_394_2 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_394_2());
        Logger.close();
    }

//    public double d = 1;
//    public double d = 2;
    public double d = 7.5;
//    public double d = 40;

    public long n = 10000000L;

    public long zn = (long) (n/d);

    MathContext mc = new MathContext(13);
    public BigDecimal zn2 = new BigDecimal(2*zn, mc);

    public void solving() {
        BigDecimal fk = new BigDecimal("1", mc);
        BigDecimal fs = new BigDecimal("0", mc);
        BigDecimal two = new BigDecimal(2, mc);
        long begk = zn + 1;

        for (long k = begk, p = zn+1; k <= n; ++k, ++p) {
            if (p >= 1000000) {
                p = 0;
                System.out.println("Progress: " + k + " " + fk.toString() + " " + fs.toString());
//                System.out.println("        : " + k + " " + fk.precision() + " " + fs.precision());
            }
            BigDecimal ka = new BigDecimal(k, mc);
            BigDecimal k2 = new BigDecimal(k*k, mc);

            fk = fk.multiply(k2.subtract( two.multiply(ka) ).add(two)).add( two.multiply(fs).add(ONE) ).add( zn2 );
            fk = fk.divide(k2, mc);
            fs = fs.add(fk.add(ONE), mc);

//            fk = (fk*(k*k - 2*k + 2) + (2*fs + 1) + 2*zn)/k2;
//            fs += fk + 1;
        }
        System.out.println(fk);
    }
}
