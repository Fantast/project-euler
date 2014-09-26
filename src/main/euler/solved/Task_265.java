package solved;

import tasks.ITask;
import tasks.Tester;
import utils.MyMath;
import utils.log.Logger;

import java.util.Arrays;

//Answer : 209110240768
public class Task_265 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_265());
        Logger.close();
    }

    int N = 5;
    int len = 1 << N;
    int slen = len - N;
    int max = 1 << slen;
    int nmask = ((1<<N)-1);
    
    public void solving() {
        long res = 0;
        for (int i = 1; i < max; ++i) {
            boolean isOk = true;
            fillBits(i);

            int n = 0;
            check[n] = true;

            for (int j = N; j < N + len - 1; ++j) {
                int ind = j%len;
                n = (n << 1) & nmask;
                if (bits[ind]) n++;

                if (check[n]) {
                    isOk = false;
                    break;
                }
                check[n] = true;
            }
            if (isOk) {
                res += i;
            }

        }
        
        System.out.println(res);
    }

    boolean check[] = new boolean[len+1];
    boolean bits[] = new boolean[len];
    private void fillBits(int n) {
        Arrays.fill(check, false);
        for (int i = 0; i < slen; ++i) {
            bits[N + i] = MyMath.isBitSet(n, slen - 1 - i);
        }
    }
}
