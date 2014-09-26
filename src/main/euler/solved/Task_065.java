package solved;

import tasks.ITask;
import tasks.Tester;
import utils.Fraction;
import utils.log.Logger;

//Answer: 272
public class Task_065 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_065());
        Logger.close();
    }

    public void solving() {
        Fraction E = getEFraction(1, 99).add(new Fraction(2, 1) );

        String numer = E.numer.toString();

        System.out.println("E = " + E);

        int res = 0;
        for (int i = 0; i < numer.length(); ++i) {
            res += numer.charAt(i) - '0';
        }
        System.out.println(res);
    }

    private static Fraction getEFraction(int k, int endWith) {
        Fraction res = k % 3 != 2
                       ? new Fraction(1, 1)
                       : new Fraction(2 * (k / 3 + 1), 1);

        if (k == endWith) return res.reciprocal();

        return res.add( getEFraction(k+1, endWith) ).reciprocal();
    }
}
