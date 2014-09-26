package solved;

import tasks.ITask;

import java.util.ArrayList;

import static utils.MyMath.getPrimeDivisors;
import static utils.MyMath.getPrimesBetween;

//Answer : 453647705
public class Task_133 implements ITask {
    private static final long LIMIT = 100000L ;

    public void solving() {
        long res = 0;
        long prs[] = getPrimesBetween(0, LIMIT);

        for (int i = 0; i < prs.length; ++i) {
            int prime = (int) prs[i];
            if (!canBeFactor(prime)) {
//                System.out.println(prime);
                res += prs[i];
            }
        }

        System.out.println("Sum: " + res );
    }

    private boolean canBeFactor(int n) {
        int r = 1;
        for (int i = 2; i <= n; ++i) {
            r = (10*r+1) % n;
            if (r == 0) {
                ArrayList<Long> pds = getPrimeDivisors(i);
                if (pds.size() > 2) return false;
                if (pds.size() == 2) {
                    long p1 = pds.get(0);
                    long p2 = pds.get(1);
                    return p1*p2 == 10;
                } else {
                    long p1 = pds.get(0);
                    return p1==2 || p1==5;
                }
            }
        }

        return false;
    }
}
