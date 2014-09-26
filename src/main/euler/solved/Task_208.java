package solved;

import tasks.ITask;
import tasks.Tester;

import static utils.OtherUtils.deepFillLong;

//Answer : 331951449665644800
public class Task_208 implements ITask {
    int n = 70;
    int n1 = n+1;
    int n5 = n / 5;

    long p[][][][][][];
    public void solving() {
        p = new long[5][][][][][];
        for (int s = 0; s < 5; ++s) {
            p[s] = new long[n1][][][][];
            for (int i = 0; i < n1; ++i) {
                p[s][i] = new long[n1 - i][][][];
                for (int j = 0; j < n1 - i; ++j) {
                    p[s][i][j] = new long[n1 - i - j][][];
                    for (int k = 0; k < n1 - i - j; ++k) {
                        p[s][i][j][k] = new long[n1 - i - j - k][];
                        for (int l = 0; l < n1 - i - j - k; ++l) {
                            p[s][i][j][k][l] = new long[n1 - i - j - k - l];
                        }
                    }
                }
            }
        }
        deepFillLong(p, -1);
        System.out.println(find(0, 0, 0, 0, 0, 0));
    }

    private long find(int dr, int a, int b, int c, int d, int e) {
        if (a > n5 || b > n5 || c > n5 || d > n5 || e > n5) return 0;
        if (a == n5 && b == n5 && c == n5 && d == n5 && e == n5) return 1;

        if (p[dr][a][b][c][d][e] != -1) {
            return p[dr][a][b][c][d][e];
        }

        if (dr == 0) return p[dr][a][b][c][d][e] = find(1,a+1,b,c,d,e) + find(4,a,b,c,d,e+1);
        if (dr == 1) return p[dr][a][b][c][d][e] = find(2,a,b+1,c,d,e) + find(0,a+1,b,c,d,e);
        if (dr == 2) return p[dr][a][b][c][d][e] = find(3,a,b,c+1,d,e) + find(1,a,b+1,c,d,e);
        if (dr == 3) return p[dr][a][b][c][d][e] = find(4,a,b,c,d+1,e) + find(2,a,b,c+1,d,e);
        if (dr == 4) return p[dr][a][b][c][d][e] = find(0,a,b,c,d,e+1) + find(3,a,b,c,d+1,e);
        return 0;
    }

    public static void main(String[] args) {
        Tester.test(new Task_208());
    }
}
