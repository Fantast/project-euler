package solved;

import tasks.ITask;
import tasks.Tester;
import utils.Fraction;
import utils.log.Logger;

public class Task_057 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_057());
        Logger.close();
    }

    int LIM = 1000;
    Fraction TWO = new Fraction(2, 1);
    public void solving() {
        long res = 0;
        Fraction current = new Fraction(1, 2);
        for (int i = 1; i <= LIM; ++i) {
            if ( check(current) ) ++res;
            current = current.add( TWO ).reciprocal();
        }

        System.out.println(res);
    }

    boolean check(Fraction f) {
        f = f.add( Fraction.ONE );
//        System.out.println(f);
        return f.numer.toString().length() > f.denom.toString().length();
    }
}
