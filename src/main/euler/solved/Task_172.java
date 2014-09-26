package solved;

import tasks.ITask;
import tasks.Tester;

//Answer : 227485267000992000
public class Task_172 implements ITask {

    int n = 18;

    int hashCnt = 1<<20;
    long rs[][][] = new long[n][10][hashCnt];

    int p4[] = new int[11];
    public void solving() {
        p4[0] = 1;
        for (int i = 1; i < p4.length; ++i) {
            p4[i] = p4[i-1] * 4;
        }

        for (int d = 1; d < 10; ++d) {
            rs[0][d][setHashDigit(0, d, 1)] = 1;
        }

        for (int i = 1; i < n; ++i) {
            System.out.println("Progress: " + i);
            for (int d = 0; d < 10; ++d) {
                for (int h = 1; h < hashCnt; ++h) {
                    int ch = getHashDigit(h, d);
                    if (ch < 3) {
                        int hn = setHashDigit(h, d, ch+1);
                        for (int dn = 0; dn < 10; ++dn) {
                            rs[i][d][hn] += rs[i-1][dn][h];
                        }
                    }
                }
            }
        }

        long res = 0;
        for (int d = 0; d < 10; ++d) {
            for (int h = 1; h < hashCnt; ++h) {
                res += rs[n-1][d][h];
            }
        }

        System.out.println(res);
    }

    public static void main(String[] args) {
        Tester.test(new Task_172());
    }

    private int setHashDigit(int h, int d, int c) {
        return h + (c - getHashDigit(h, d))*p4[d];
    }

    private int getHashDigit(int h, int d) {
        return (h / p4[d]) % 4;
    }
}
