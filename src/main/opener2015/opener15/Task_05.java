package opener15;

import tasks.ITask;
import tasks.Tester;
import utils.log.Logger;

import java.util.*;

//Answer : 10101804
public class Task_05 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_05());
        Logger.close();
    }

    String w1 = "GREATPEOPLE";
    String w2 = "ITRANSITION";
    String w3 = "DEVELOPMENT";

    public void solving() {
        find(w1.length() - 1, 0);
    }

    Map<Character, Integer> digits = new HashMap<>();
    Map<Integer, Character> chars = new HashMap<>();

    private void find(int ind, int carry) {
        if (ind == -1) {
            if (carry == 0) {
                result();
            }
            return;
        }

        char c1 = w1.charAt(ind);
        char c2 = w2.charAt(ind);
        char c3 = w3.charAt(ind);

        Integer d1 = digits.get(c1);
        Integer d2 = digits.get(c2);
        Integer d3 = digits.get(c3);

        if (d1 == null && d2 == null) {
            for (int nd1 = 0; nd1 < 16; nd1++) {
                if (!chars.containsKey(nd1)) {
                    link(c1, nd1);
                    if (c1 != c2) {
                        for (int nd2 = 0; nd2 < 16; nd2++) {
                            if (!chars.containsKey(nd2)) {
                                link(c2, nd2);
                                next(nd1, nd2, c3, d3, carry, ind);
                                unlink(c2, nd2);
                            }
                        }
                    } else {
                        next(nd1, nd1, c3, d3, carry, ind);
                    }
                    unlink(c1, nd1);
                }
            }
        } else if (d1 == null) {
            for (int nd1 = 0; nd1 < 16; nd1++) {
                if (!chars.containsKey(nd1)) {
                    link(c1, nd1);
                    next(nd1, d2, c3, d3, carry, ind);
                    unlink(c1, nd1);
                }
            }
        } else if (d2 == null) {
            for (int nd2 = 0; nd2 < 16; nd2++) {
                if (!chars.containsKey(nd2)) {
                    link(c2, nd2);
                    next(d1, nd2, c3, d3, carry, ind);
                    unlink(c2, nd2);
                }
            }
        } else {
            next(d1, d2, c3, d3, carry, ind);
        }
    }

    private void next(int d1, int d2, char c3, Integer d3, int carry, int ind) {
        int nd3 = (d1 + d2 + carry) % 16;
        int nc = (d1 + d2 + carry) / 16;

        if (d3 != null) {
            if (nd3 != d3) {
                return;
            }
            find(ind - 1, nc);
        } else if (!chars.containsKey(nd3)) {
            link(c3, nd3);
            find(ind - 1, nc);
            unlink(c3, nd3);
        }
    }

    private void link(char ch, int digit) {
        digits.put(ch, digit);
        chars.put(digit, ch);
    }

    private void unlink(char ch, int digit) {
        digits.remove(ch);
        chars.remove(digit);
    }

    private String rr(String w) {
        String number = "";
        for (char c : w.toCharArray()) {
            Integer digit = digits.get(c);
            number += digit == null ? "?" : Integer.toHexString(digit);
        }
        return number.toUpperCase();
    }

    private void result() {
        System.out.println(Integer.parseInt(rr("OPENER"), 16));
    }
}
