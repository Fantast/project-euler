package opener15;

import org.apfloat.Apfloat;
import org.apfloat.ApfloatMath;
import tasks.ITask;
import tasks.Tester;
import utils.log.Logger;

//Answer :
@SuppressWarnings("SuspiciousNameCombination")
public class Task_04 implements ITask {

    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_04());
        Logger.close();
    }

    private final long precision = 300;
    private final Apfloat ONE = new Apfloat(1, precision);
    private final Apfloat TWO = new Apfloat(2, precision);
    private final Apfloat T3 = new Apfloat(3, precision);

    public void solving() {
        Apfloat eps = new Apfloat("1e-35", precision);

        Apfloat l = new Apfloat("0.990", precision);
        Apfloat r = new Apfloat("0.999", precision);

        while (r.subtract(l).compareTo(eps) > 0) {
            Apfloat m1 = l.multiply(TWO).add(r).divide(T3);
            Apfloat m2 = l.add(r.multiply(TWO)).divide(T3);

            Apfloat fm1 = f(m1);
            Apfloat fm2 = f(m2);

            if (fm1.compareTo(fm2) < 0) {
                r = m2;
            } else {
                l = m1;
            }
        }
        System.out.println(l.toString(true));
        System.out.println(r.toString(true));
    }

    public Apfloat f(Apfloat x) {
        Apfloat num = ApfloatMath.pow(x, 2015).subtract(ONE).multiply(x);

        Apfloat x2 = x.multiply(x);
        Apfloat den = ApfloatMath.pow(x2, x2).add(ONE);

        return num.divide(den);
    }
}
