package solved;

import tasks.ITask;
import tasks.Tester;

import java.util.Iterator;
import java.util.TreeSet;

//Answer : 5673835352990
//@see for solving Diophantine equation: http://www.alpertron.com.ar/QUAD.HTM
public class Task_140 implements ITask {
    public void solving() {
        long X[] = {0, 0, -3, -3, -4, -4, 2, 2};
        long Y[] = {-1, 1, -2, 2, -5, 5, -7, 7};

        TreeSet<Long> all = new TreeSet<Long>();
        for (int i = 0; i < 30; ++i) {
            for (int j = 0; j < X.length; ++j) {
                long x = X[j];
                long y = Y[j];

                long xn = -9 * x - 4 * y - 14;
                long yn = -20 * x - 9 * y - 28;

                X[j] = xn;
                Y[j] = yn;

                if (xn > 0) all.add(xn);
            }
        }

        long res = 0;
        Iterator<Long> it = all.iterator();
        for (int i = 0; i < 30; ++i) {
            long n = it.next();
            res += n;
        }

        System.out.println(res);
   }

    public static void main(String[] args) {
        Tester.test(new Task_140());
    }
}