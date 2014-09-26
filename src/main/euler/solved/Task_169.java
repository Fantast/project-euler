package solved;

import tasks.ITask;
import tasks.Tester;

import java.util.Arrays;

import static java.math.BigInteger.TEN;

//Answer : 178653872807
public class Task_169 implements ITask {
    public void solving() {
        Arrays.fill(d, -1);
        System.out.println(count(TEN.pow(25).toString(2), 0));
    }

    long d[] = new long[200];
    private long count(String s, int i) {
        if (i == s.length()) return 1;
        if (d[i] != -1) return d[i];

        int j, k;
        long r = 0;

        j = nextInd(s, i+1, '1');
        if (j == s.length()) return d[i] = j - i;

        int c = j - i;

        k = nextInd(s, j+1, '1');
        if (k != s.length()) {
            r += c * count(s, k);
        } else {
            r = c;
        }

        k = nextInd(s, j+1, '0');
        if (k != s.length()) {
            r += (c+1)*count(s, k);
        }
        
        return d[i] = r;
    }

    private int nextInd(String s, int beg, char ch) {
        for (int k = beg; k < s.length(); ++k) {
            if (s.charAt(k) == ch) {
                return k;
            }
        }
        return s.length();
    }

    public static void main(String[] args) {
        Tester.test(new Task_169());
    }
}