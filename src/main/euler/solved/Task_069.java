package solved;

import tasks.ITask;

import static utils.MyMath.getPrimeDivisors;
import static utils.MyMath.phi;

//Answer : 510510
public class Task_069 implements ITask {
    public void solving() {
        long res = 0;

        double rel = 0;
        for (int i = 2; i <= 1000000; ++i) {
            double nr = i/(double)phi(i);
            if (nr > rel) {
                rel = nr;
                res = i;
            }
        }
        System.out.println( res );
        System.out.println( getPrimeDivisors(res) );
    }
}
