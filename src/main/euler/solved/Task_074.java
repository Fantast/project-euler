package solved;

import tasks.ITask;
import tasks.Tester;
import utils.log.Logger;

import java.util.HashSet;

//Answer : 402
public class Task_074 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_074());
        Logger.close();
    }

    final int MAX = 1000000;
    int next[] = new int[4000000];
    int len[] = new int[4000000];

    int facts[] = new int[10];

    public void solving() {
        for (int i = 0; i <= 9; ++i) facts[i] = (i!=0)?i*facts[i-1]:1;

        int res = 0;
        HashSet<Integer> used = new HashSet<Integer>();
        for (int i = 1; i <= MAX; ++i) {
            int n = i;
            do {
                used.add(n);
                if (used.size() > 60) break;
                n = next(n);
            } while (!used.contains(n));
            if (used.size() == 60) res++;
            used.clear();
        }

        System.out.println(res);
    }

    private int next(int n) {
        if (next[n] != 0) return next[n];
        int res = 0;
        while (n!=0) {
            res += facts[n%10];
            n /= 10;
        }

        return next[n] = res;
    }

}
