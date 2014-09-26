package solved;

import tasks.ITask;
import tasks.Tester;

import static java.lang.Math.max;

//Answer : 14489159
public class Task_159 implements ITask {
    int LIM = 1000000;
    long mdrs[] = new long[LIM];
    long dr[] = new long[LIM];
    public void solving() {
        for (int i = 1; i < LIM; ++i) {
            dr[i] = (i < 10) ? i : dr[ dr(i) ];
        }

        long res = 0;
        for (int i = 2; i < LIM; ++i) {
            mdrs[i] = max(mdrs[i], dr[i]);

            for (int j = 2; j <= i && i*j < LIM; ++j) {
                mdrs[i*j] = max(mdrs[i*j], mdrs[i] + mdrs[j]);
            }
            
            res = res + mdrs[i];
        }
        System.out.println(mdrs[24]);
        System.out.println(res);
    }

    private int dr(int i) {
        // fun formula
        return i - (i-1)/9 * 9;
//        int r = 0;
//        while (i != 0) {
//            r += i % 10;
//            i /= 10;
//        }
//        return r;
    }

    public static void main(String[] args) {
        Tester.test(new Task_159());
    }
}
