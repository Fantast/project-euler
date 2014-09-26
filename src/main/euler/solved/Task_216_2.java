package solved;

import tasks.ITask;
import tasks.Tester;
import utils.MyMath;

import static utils.MyMath.*;

//Answer : 5437849
//using: http://en.wikipedia.org/wiki/Shanks-Tonelli_algorithm
public class Task_216_2 implements ITask {

    private int LIM = 50000000;

    boolean composite[] = new boolean[LIM + 1];
    long primes[];

    public void solving() {
        primes = MyMath.getPrimesBetween(3, (long) (LIM*Math.sqrt(2)) + 1);

        for (long p : primes) {
            long k = (p+1)/2;
//            if (modPow(k, (p-1)/2, p) == 1) {
            if( p%8==7 || p%8==1 ) {
                int x1 = (int) modSqrt((p + 1) / 2, p);
                int x2 = (int) (p - x1);

                uncheck(x1, (int) p);
                uncheck(x2, (int) p);
            }
        }

        int res = 0;
        for (int i = 2; i <= LIM; i++) {
            if (!composite[i]) {
                ++res;
            }
        }

        System.out.println(res);
    }

    private void uncheck(int x, int p) {
        if (x <= LIM && 2*x*x-1 != p) {
            composite[x] = true;
        }
        x += p;
        while (x <= LIM) {
            composite[x] = true;
            x += p;
        }
    }

    public static void main(String[] args) {
        Tester.test(new Task_216_2());
    }
}
