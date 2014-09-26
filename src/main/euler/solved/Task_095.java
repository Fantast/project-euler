package solved;

import tasks.ITask;
import tasks.Tester;

import static utils.MyMath.getProperDivisorsSum;

//Answer : 14316
public class Task_095 implements ITask {

    int MAX = 1000000;
    int to[] = new int[MAX + 1];
    int len[] = new int[MAX + 1];
    int ind[] = new int[MAX + 1];
    
    boolean used[] = new boolean[MAX+1];

    public void solving() {
        System.out.println("Init...");
        for (int i = 2; i <= MAX; ++i) {
            to[i] = (int) getProperDivisorsSum(i);
        }

        System.out.println("Finding...");
        for (int i = 2; i <= MAX; ++i) {
            if (len[i] == 0) {
                fill(i, 0);
            }
        }

        int maxc = 0;
        int minn = 10;
        for (int i = 2; i <= MAX; ++i) {
            if (maxc < len[i]) {
                maxc = len[i];
                minn = i;
            }
        }

        System.out.println(minn);
    }

    public static void main(String[] args) {
        Tester.test(new Task_095());
    }

    int BEG = -1;
    int LENGTH = -1;

    private void fill(int n, int cnt) {
        if (n > MAX) return;
        if (len[n] == -1) return;
        
        if (used[n]) {
            BEG = n;
            LENGTH = cnt - ind[n];
            return;
        }

        ind[n] = cnt;
        used[n] = true;
        fill(to[n], cnt+1);
        used[n] = false;

        if (BEG != -1) {
            len[n] = LENGTH;
        } else {
            len[n] = -1;
        }
        if (BEG == n) {
            BEG = -1;
        }
    }
}
