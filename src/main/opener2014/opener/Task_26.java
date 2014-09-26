package opener;

import tasks.ITask;
import tasks.Tester;
import utils.log.Logger;

import java.util.HashSet;

//Answer :
public class Task_26 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_26());
        Logger.close();
    }

    byte s[] = new byte[]{4, 7, 5, 8, 10, 1, 15, 3, 9, 6, 11, 13, 12, 2, 0, 14};
//        byte d[] = new byte[] {4, 7, 5, 8, 10, 1, 15, 3, 9, 6, 11, 13, 0, 12, 2, 14};
//        byte d[] = new byte[] {4, 7, 5, 8, 10, 1, 15, 3, 0, 6, 11, 13, 9, 12, 2, 14};
    byte d[] = new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 0};

    long ss = state(s);
    long sd = state(d);

    public void solving() {
        for (int e : new byte[] {11 , 13 , 14 , 11 , 2 , 12 , 9 , 10 , 1 , 6 , 13 , 2 , 12 , 13 , 2 , 14 , 11 , 12 , 14 , 15 , 6 , 7 , 5 , 6 , 7 , 5 , 4 , 1 , 5 , 2 , 10 , 9 , 13 , 14 , 15 , 7 , 6 , 4 , 2 , 6 , 3 , 8 , 4 , 3 , 7 , 11 , 12}) {
            System.out.print(Integer.toString(e, 16).toUpperCase());
        }


//        find(3, 2, 10, 0, false);
//        System.out.println(best);
//        System.out.println(Arrays.toString(bestpath));
    }

    int best = 35; //6 sec
    byte[] path = new byte[best];
    byte[] bestpath = new byte[best];
    int up = 1;
    int down = 2;
    int left = 3;
    int right = 4;
    HashSet<Long> was = new HashSet<>();

    private void find(int r, int c, int deny, int moves, boolean move8) {
        if (moves >= best) {
            return;
        }
        if (ss == sd) {
            System.arraycopy(path, 0, bestpath, 0, best);
            best = moves;
            return;
        }

        if (was.contains(ss)) {
            return;
        }

        was.add(ss);

        if (r > 0 && deny != up && !(move8 && s(r - 1, c) == 8)) {
            byte se = s(r - 1, c);
            path[moves] = se;
            move(r, c, r - 1, c);
            find(r - 1, c, down, moves + 1, se == 8);
            move(r - 1, c, r, c);
        }
        if (r < 3 && deny != down && !(move8 && s(r + 1, c) == 8)) {
            byte se = s(r + 1, c);
            path[moves] = se;
            move(r, c, r + 1, c);
            find(r + 1, c, up, moves + 1, se == 8);
            move(r + 1, c, r, c);
        }
        if (c > 0 && deny != left && !(move8 && s(r, c - 1) == 8)) {
            byte se = s(r, c - 1);
            path[moves] = se;
            move(r, c, r, c - 1);
            find(r, c - 1, right, moves + 1, se == 8);
            move(r, c - 1, r, c);
        }
        if (c < 3 && deny != right && !(move8 && s(r, c + 1) == 8)) {
            byte se = s(r, c + 1);
            path[moves] = se;
            move(r, c, r, c + 1);
            find(r, c + 1, left, moves + 1, se == 8);
            move(r, c + 1, r, c);
        }
    }

    private long state(byte[] s) {
        long r = 0;
        for (int i = s.length - 1; i >= 0; i--) {
            long se = s[i];
            r = (r << 4L) + se;
        }
        return r;
    }


    private void move(int re, int ce, int rn, int cn) {
        int in = ind(rn, cn);
        sets(ind(re, ce), s(in));
        unsets(in);
    }

    private void sets(long ind, byte v) {
        ss |= (long)v << ind;
    }

    private void unsets(long ind) {
        ss = (ss & (~(15L << ind)));
    }

    private byte s(int r, int c) {
        return s(ind(r, c));
    }

    private byte s(long ind) {
        return (byte) ((ss >> ind) & 0b1111L);
    }

    private int ind(int r, int c) {
        return r * 16 + c*4;
    }
}
