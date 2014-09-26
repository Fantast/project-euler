package solved;

import tasks.ITask;
import tasks.Tester;
import utils.log.Logger;

//Answer : 1.002322108633
public class Task_235 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_235());
        Logger.close();
    }

    double NEED = -600000000000L;
    double EPS = 1e-15;
    public void solving() {
        double l = 1.0;
        double r = 1.003;

        System.out.println(f(l) - NEED);
        System.out.println(f(r) - NEED);

        while (r-l >= EPS) {
            double m = (l+r)/2;

            double fm = f(m);

            if (fm > NEED) {
                l = m;
            } else {
                r = m;
            }
        }


        System.out.println(l);
        System.out.println(r);
        
        System.out.println(f(1.0023221086328762));
    }

    private double f(double r) {
        double rp = 1;
        double s = 0;
        for (int k = 1; k <= 5000; ++k) {
            s += (900-3*k) * rp;
            rp *= r;
        }
        return s;
    }
}
