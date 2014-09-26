package solved;

import tasks.ITask;
import tasks.Tester;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

//Answer : 96356848
public class Task_244 implements ITask {

    static int di[] = {-1, +1, 0, 0};
    static int dj[] = {0, 0, +1, -1};
    static char dirs[] = {'D', 'U', 'L', 'R'};
    int T[][] = {
        {2, 1, 0, 1},
        {1, 0, 1, 0},
        {0, 1, 0, 1},
        {1, 0, 1, 0}
    };

    int S[][] = {
        {2, 0, 1, 1},
        {0, 0, 1, 1},
        {0, 0, 1, 1},
        {0, 0, 1, 1}
    };
    State s = new State(S);
    State t = new State(T);
    HashMap<State, Integer> best = new HashMap<State, Integer>();
    Queue<State> q = new LinkedList<State>();
    int min = 0;
    long res = 0;

    public void solving() {
        best.put(s, 0);
        q.add(s);
        QUEUE:
        while (!q.isEmpty()) {
            State st = q.poll();

            int curr = best.get(st);
            for (State ns : st.getAdjustment()) {
                if (!best.containsKey(ns)) {
                    best.put(ns, curr + 1);

                    if (ns.equals(t)) {
                        min = curr + 1;
                        break QUEUE;
                    }

                    q.add(ns);
                }
            }
        }

        dfs(s, 0, "");

        System.out.println(res);
    }

    void dfs(State s, int steps, String path) {
        if (s.equals(t)) {
            System.out.println(path);
            res += checkSum(path);
            return;
        }
        ++steps;
        for (State ns : s.getAdjustment()) {
            Integer nbest = best.get(ns);
            if (nbest != null && nbest == steps) {
                dfs(ns, steps, path + ns.dir);
            }
        }
    }

    public static void main(String[] args) {
        Tester.test(new Task_244());
    }

    private long checkSum(String path) {
        long sum = 0;
        for (int i = 0; i < path.length(); ++i) {
            sum = (sum * 243 + (int) path.charAt(i)) % 100000007;
        }
        return sum;
    }

    class State {

        int s[][];
        int ei, ej;
        private int hash;
        public char dir;

        public State(int s[][], int ei, int ej, char dir) {
            this.dir = dir;
            this.s = s;
            this.ei = ei;
            this.ej = ej;
            this.hash = hash(s);
        }

        public State(int s[][]) {
            this.s = new int[4][4];
            for (int i = 0; i < 4; ++i) {
                for (int j = 0; j < 4; ++j) {
                    this.s[i][j] = s[i][j];
                    if (s[i][j] == 2) {
                        ei = i;
                        ej = j;
                    }
                }
            }
            this.hash = hash(s);
        }

        @Override
        public int hashCode() {
            return hash;
        }

        public int hash(int m[][]) {
            int hash = 0;
            int p = 1;
            for (int i = 0; i < 4; ++i) {
                for (int j = 0; j < 4; ++j) {
                    hash += m[i][j] * p;
                    p *= 3;
                }
            }
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            State oth = (State) obj;
            return hash == oth.hash;
        }

        private Iterable<State> getAdjustment() {
            ArrayList<State> res = new ArrayList<State>();
            for (int i = 0; i < di.length; ++i) {
                int ni = ei + di[i];
                int nj = ej + dj[i];

                if (ni < 0 || ni > 3 || nj < 0 || nj > 3) {
                    continue;
                }

                res.add(swapState(ni, nj, dirs[i]));
            }

            return res;
        }

        private State swapState(int ni, int nj, char dir) {
            int sn[][] = new int[4][4];

            for (int i = 0; i < 4; ++i) {
                for (int j = 0; j < 4; ++j) {
                    sn[i][j] = s[i][j];
                }
            }

            sn[ei][ej] = sn[ni][nj];
            sn[ni][nj] = 2;

            return new State(sn, ni, nj, dir);
        }
    }
}
