package opener;

import tasks.ITask;
import tasks.Tester;
import utils.log.Logger;

//Answer :
public class Task_25 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_25());
        Logger.close();
    }

    String need = "11111011110010";
//    String need = "1111100";
    int maxlen = need.length() + 12*2;
    String best = "AAAAAAAAAAAAAAAAAAAAAA";

    public void solving() {

        find("1", "");

        System.out.println(best);
        System.out.println(apply("1", best));
    }



    private void find(String s, String curr) {
        if (curr.length() > 12) {
            return;
        }
        if (s.length() > maxlen) {
            return;
        }

        if (curr.length() >= best.length()) {
            return;
        }

        if (s.equals(need)) {
            System.out.println(curr);
            best = curr;
            return;
        }

        find(s+s, curr + "B");
        if (s.endsWith("1")) {
            find(s + "0", curr + "A");
        }
        if (s.length() > 1) {
            boolean zero1 = s.charAt(0) == '0';
            boolean zero2;
            for (int i = 1; i < s.length(); ++i) {
                zero2 = s.charAt(i) == '0';
                if (zero1 && zero2) {
                    find(s.substring(0, i-1) + s.substring(i+1), curr + "D" + (i-1));
                }
                zero1 = zero2;
            }
            if (s.length() > 2) {
                boolean one1 = s.charAt(0) == '1';
                boolean one2 = s.charAt(1) == '1';
                boolean one3;
                for (int i = 2; i < s.length(); ++i) {
                    one3 = s.charAt(i) == '1';
                    if (one1 && one2 && one3) {
                        find(s.substring(0, i-2) + "0" + s.substring(i+1), curr + "C" + (i-2));
                    }
                    one1 = one2;
                    one2 = one3;
                }
            }
        }
    }

    private String apply(String s, String m) {
        StringBuilder res = new StringBuilder(s);
        for (int i = 0; i < m.length(); ++i) {
            char ch = m.charAt(i);
            if (ch == 'B') {
                res = res.append(res.toString());
            } else if (ch == 'A') {
                res = res.append("0");
            } else if (ch == 'C') {
                char ch2 = m.charAt(++i);
                int ind = 0;
                while (Character.isDigit(ch2)) {
                    ind = ind * 10 + (ch2 - '0');
                    if (i == m.length() - 1) {
                        break;
                    }
                    ch2 = m.charAt(++i);
                }
                if (!res.substring(ind, ind + 3).equals("111")) {
                    throw new IllegalStateException("C..");
                }
                res.replace(ind, ind + 3, "0");
                if (i == m.length() - 1) {
                    break;
                }
                --i;
            } else if (ch == 'D') {
                int ind = 0;
                char ch2 = m.charAt(++i);
                while (Character.isDigit(ch2)) {
                    ind = ind * 10 + (ch2 - '0');
                    if (i == m.length() - 1) {
                        break;
                    }
                    ch2 = m.charAt(++i);
                }
                if (!res.substring(ind, ind + 2).equals("00")) {
                    throw new IllegalStateException("D..");
                }
                res.replace(ind, ind + 2, "");
                if (i == m.length() - 1) {
                    break;
                }
                --i;
            }
        }
        return res.toString();
    }
}
