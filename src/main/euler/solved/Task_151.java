package solved;

import tasks.ITask;
import tasks.Tester;
import utils.log.Logger;

//Answer : 0.464399
public class Task_151 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_151());
        Logger.close();
    }


    public void solving() {
        System.out.println( getProbability(1, 1, 1, 1 ) );
    }

    double cache[] = new double[70000];
    double getProbability(int a2, int a3, int a4, int a5) {
        int index = a2*16*16*16 + a3*16*16 + a4*16 + a5;
        if (index == 1) return 0;
        if (cache[ index ] != 0) return cache[ index ];

        int sum = a2+a3+a4+a5;
        double res = (sum==1) ? 1 : 0;

        if (a2!=0) res += a2*getProbability( a2-1, a3+1, a4+1, a5+1 );
        if (a3!=0) res += a3*getProbability( a2, a3-1, a4+1, a5+1 );
        if (a4!=0) res += a4*getProbability( a2, a3, a4-1, a5+1 );
        if (a5!=0) res += a5*getProbability( a2, a3, a4, a5-1 );

        return cache[index] = res/sum;
    }
}
