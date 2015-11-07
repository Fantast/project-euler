package solved;

import tasks.AbstractTask;
import tasks.Tester;
import utils.MyMath;
import utils.log.Logger;

import java.util.HashMap;
import java.util.Map;

//Answer : 15836928
//see: http://habrahabr.ru/post/105705/
public class Task_237 extends AbstractTask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_237());
        Logger.close();
    }


    String tosArray[] = new String[] {
            "( ) ( )", "()()",
            "( )_( )", "(  )",
            "( ) ._.", "()()",
            "( )_._.", "(  )",
            "( ( ) )", "(())",
            "(_( ) )", "  ()",
            "( ( )_)", "()  ",
//        "(_( )_)", "    ",
            "(_. )_.", " ( )",
            "( ._. )", "(())",
            "(_._. )", "  ()",
            "(_. ._)", " () ",
            "( ._._)", "()  ",
//        "(_._._)", "",
            "._( )_.", "(  )",
            "._( ._)", "( ) ",
            "._. ( )", "()()",
            "._._( )", "(  )",
            "._. ._.", "()()",
            "._._._.", "(  )"
    };

    Map<String, Integer> links = new HashMap<>();

//    long n = 10;
    long n = 1000000000000L;

    long MOD = 100000000;

    State states[] = new State[9];
    int stateCnt = 0;
    long T[][];
    long R[][];
    long out[][];
    long tmp[][];

    String need = "(  )";
    int needi = -1;

    // solving as Nx4, not 4xN
    public void solving() {
        generateStates(0, 0);

        for (int i = 0; i < stateCnt; ++i) {
            if (states[i].is(need)) {
                needi = i;
                break;
            }
        }

        assert needi != -1;

        for (int i = 0; i < tosArray.length; i += 2) {
            String curr = tosArray[i];
            String to = tosArray[i+1];
            int sti;
            for (sti = 0; sti < stateCnt; ++sti) {
                if (states[sti].is(to)) {
                    break;
                }
            }
            assert sti < stateCnt;
            links.put(curr, sti);
        }

        T = new long[stateCnt][stateCnt];
        R = new long[stateCnt][stateCnt];
        tmp = new long[stateCnt][stateCnt];
        out = new long[stateCnt][stateCnt];
        for (int i = 0; i < stateCnt; ++i) {
            State state = states[i];
            R[i][i] = 1;
            for (int horz = 0; horz <= 0b111; ++horz) {
                int stateTo = stateTo(state, horz);
                if (stateTo != -1) {
                    T[i][stateTo]++;
                }
            }
        }

        while (n != 0) {
            if ((n&1) == 1) {
                multiply(R, T, out);
                tmp = out;
                out = R;
                R = tmp;
            }
            n >>= 1;
            multiply(T, T, out);
            tmp = out;
            out = T;
            T = tmp;
        }

        System.out.println(R[0][needi]);
    }

    public void add(long to[][], long what[][]) {
        for (int row = 0; row < stateCnt; ++row) {
            for (int col = 0; col < stateCnt; ++col) {
                to[row][col] += what[row][col];
            }
        }
    }

    public void multiply(long a[][], long b[][], long out[][]) throws IllegalArgumentException {
        for (int row = 0; row < stateCnt; ++row) {
            for (int col = 0; col < stateCnt; ++col) {
                long sum = 0;
                for (int i = 0; i < stateCnt; ++i) {
                    sum = (sum + a[row][i] * b[i][col]) % MOD;
                }
                out[row][col] = sum;
            }
        }
    }

    private int stateTo(State state, int horz) {
        int[] s = state.state;

        for (int i = 0; i < 3; ++i) {
            boolean h = MyMath.isBitSet(horz, i);
            if (h && s[i] == OPENED && s[i+1] == CLOSED) {
                return -1;
            }
        }

        boolean prevh = false;
        for (int i = 0; i < 4; ++i) {
            boolean h = MyMath.isBitSet(horz, i);
            if (prevh && h && s[i] != EMPTY) {
                return -1;
            }
            if (!prevh && !h && s[i] == EMPTY) {
                return -1;
            }

            prevh = h;
        }

        String curr = "";
        for (int i = 0; i < 4; ++i) {
            if (s[i] == OPENED) {
                curr += "(";
            } else if (s[i] == CLOSED) {
                curr += ")";
            } else {
                curr += ".";
            }
            if (i < 3) {
                boolean h = MyMath.isBitSet(horz, i);
                curr += h ? "_": " ";
            }
        }
//        System.out.println(curr);
        return links.containsKey(curr) ? links.get(curr) : -1;
    }

    int state[] = new int[4];
    static int EMPTY = 0;
    static int OPENED = 1;
    static int CLOSED = 2;
    private void generateStates(int ind, int opened) {
        if (ind == 4) {
            states[stateCnt++] = new State(this.state);
            return;
        }

        if (opened > 0) {
            state[ind] = CLOSED;
            generateStates(ind + 1, opened - 1);
        }

        if (4 - ind - 1 >= opened + 1) {
            state[ind] = OPENED;
            generateStates(ind + 1, opened + 1);
        }

        if (4 - ind - 1 >= opened) {
            state[ind] = EMPTY;
            generateStates(ind + 1, opened);
        }
    }

    static class State {
        int state[];
        public State(int[] state) {
            this.state = state.clone();
        }

        public boolean is(String s) {
            for (int i = 0; i < 4; ++i) {
                char ch = s.charAt(i);
                int symb = ch == '(' ? OPENED : (ch == ')' ? CLOSED : EMPTY);
                if (state[i] != symb) {
                    return false;
                }
            }

            return true;
        }
    }
}
