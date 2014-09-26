package solved;

import tasks.ITask;
import tasks.Tester;

import java.util.TreeSet;

//Answer : 7587457
public class Task_088 implements ITask {

    public void solving() {
        TreeSet<Integer> all = new TreeSet<Integer>();
        for (int n = 2; n <= 12000; ++n) {
            all.add( getMin(n) );
        }

        long res = 0;
        for (long p : all) {
            res += p;
        }
        System.out.println(res);
    }

    private int getMin(int n) {
        for (int k = n+1; ;++k) {
            if ( find(n, k, k) ) return k;
        }
    }

    private boolean find(int n, int sum, int prod) {
        if (sum < n) return false;
        if (prod == 1) return sum == n;
        if (n == 1) return prod == sum;

        for (int i = 2; i <= prod && sum-i >= n-1; ++i) {
            if (prod % i == 0) {
                if (find(n-1, sum-i, prod/i)) return true;;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        Tester.test(new Task_088());
    }

}
