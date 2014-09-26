package solved;

import tasks.ITask;

//Answer      : 1120149658760
//Begin with  : http://en.wikipedia.org/wiki/Fibonacci_number#Power_series
//Explanation : http://projecteuler.net/index.php?section=problems&id=137
public class Task_137 implements ITask {
    public void solving() {
        long k = 2;
        long oldK = 0;
        for (int i = 2; i < 17; ++i) {
            System.out.println( (i-1) + " : " + k );
            long newK = 7*k - (oldK-1);
            oldK = k;
            k = newK;
        }

    }
}
