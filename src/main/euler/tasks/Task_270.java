package tasks;

import static utils.OtherUtils.deepFillLong;

//Answer :
public class Task_270 implements ITask {
    public void solving() {
        deepFillLong(cn, -1);
        System.out.println(count(30, 30, 30, 30, true, true, true, true));
    }

    long cn[][][][] = new long[31][31][31][31];
    private long count(int a, int b, int c, int d,
            boolean ab, boolean bc, boolean cd, boolean da) {
        if (a==0 && b==0 && c==0 && d==0) return 1;

        if (cn[a][b][c][d] == -1) {
            long res = 0;
            int abeg = da ? 1 : 0;
            int bbeg = ab ? 1 : 0;
            int cbeg = bc ? 1 : 0;
            int dbeg = cd ? 1 : 0;

            for (int ai = abeg; ai <= a; ++ai) {
                for (int bi = bbeg; bi <= b; ++bi) {
                    
                }
            }

            cn[a][b][c][d] = res;
        }
        return cn[a][b][c][d];
    }

    public static void main(String[] args) {
        Tester.test(new Task_270());
    }
}
