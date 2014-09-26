package tasks;

import org.apfloat.Apfloat;
import utils.log.Logger;

import static org.apfloat.Apcomplex.ONE;

//Answer :
public class Task_394_3 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_394_3());
        Logger.close();
    }

//    public double d = 1;
//    public double d = 2;
    public double d = 7.5;
//    public double d = 40;

    public long n = 1000000L;

    public long zn = (long) (n/d);

    int precision = 13;
    public Apfloat zn2 = new Apfloat(2*zn, precision);

    public void solving() {
        Apfloat fk = new Apfloat("1", precision);
        Apfloat fs = new Apfloat("0", precision);
        Apfloat two = new Apfloat(2).precision(precision);
        long begk = zn + 1;

        for (long k = begk, p = zn+1; k <= n; ++k, ++p) {
            if (p >= 10000) {
                p = 0;
                System.out.println("Progress: " + k + " " + fk.toString(true) + " " + fs.toString(true));
                System.out.println("        : " + k + " " + fk.precision() + " " + fs.precision());
            }
            Apfloat ka = new Apfloat(k, precision);
            Apfloat k2 = new Apfloat(k*k, precision);

            fk = fk.multiply(k2.subtract( two.multiply(ka) ).add(two)).add( two.multiply(fs).add(ONE) ).add( zn2 );
            fk = fk.divide(k2).precision(precision);
            fs = fs.add( fk.add(ONE) ).precision(precision);

//            fk = (fk*(k*k - 2*k + 2) + (2*fs + 1) + 2*zn)/k2;
//            fs += fk + 1;
        }
        System.out.println(fk);
    }
}
