package solved;

import tasks.ITask;
import tasks.Tester;
import utils.LongFraction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

//Answer : 1258
public class Task_093 implements ITask {

    HashMap<TreeSet<Integer>, TreeSet<Integer>> all = new HashMap<TreeSet<Integer>, TreeSet<Integer>>();
    private int ERROR = 1000000;
    private LongFraction FERROR = new LongFraction(ERROR, 1);

    public void solving() {
        for (int a = 0; a <= 9; ++a) {
            for (int b = 0; b <= 9; ++b) {
                if (a == b) continue;
                for (int c = 0; c <= 9; ++c) {
                    if (a == c || b == c) continue;
                    for (int d = 0; d <= 9; ++d) {
                        if (a == d || b == d || c == d) continue;
                        addAll(getSet(a, b, c, d), a, b, c, d);
                    }
                }
            }
        }

        TreeSet<Integer> res = null;
        int maxr = 0;
        for (Map.Entry<TreeSet<Integer>, TreeSet<Integer>> entry : all.entrySet()) {
            int r = 1;
            while (entry.getValue().contains(r)) {
                ++r;
            }

            if (maxr < r) {
                res = entry.getKey();
                maxr = r;
            }
        }

        System.out.println(maxr);
        System.out.println(res);
    }

    public static void main(String[] args) {
        Tester.test(new Task_093());
    }

    private void addAll(TreeSet<Integer> st, int a, int b, int c, int d) {
        for (int s1 = 0; s1 < 4; ++s1) {
            for (int s2 = 0; s2 < 4; ++s2) {
                for (int s3 = 0; s3 < 4; ++s3) {

                    for (int p1 = 0; p1 < 3; ++p1) {
                        for (int p2 = 0; p2 < 3; ++p2) {
                            if (p2 == p1) {
                                continue;
                            }
                            for (int p3 = 0; p3 < 3; ++p3) {
                                if (p3 == p1 || p3 == p2) {
                                    continue;
                                }
                                ArrayList<Integer> ns = makeList(a, b, c, d);
                                ArrayList<Integer> ss = makeList(s1, s2, s3);
                                ArrayList<Integer> os = makeList(p1, p2, p3);

                                int n = count(ns, ss, os);
                                if (n != ERROR && n > 0) {
                                    st.add(n);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private TreeSet<Integer> getSet(int... vals) {
        TreeSet<Integer> key = new TreeSet<Integer>();
        for (int val : vals) {
            key.add(val);
        }

        TreeSet<Integer> st = all.get(key);
        if (st == null) {
            st = new TreeSet<Integer>();
            all.put(key, st);
        }

        return st;
    }

    private int count(ArrayList<Integer> ns, ArrayList<Integer> ss, ArrayList<Integer> os) {
        ArrayList<LongFraction> nsf = new ArrayList<LongFraction>();
        for (int n : ns) {
            nsf.add(new LongFraction(n, 1));
        }

        for (int i = 0; i < os.size(); ++i) {
            int si = os.get(i);
            int s = ss.get(si);
            ss.remove(si);

            LongFraction a = nsf.get(si);
            nsf.remove(si);
            LongFraction b = nsf.get(si);
            nsf.remove(si);

            LongFraction r = count(a, b, s);
            if (r.equals(FERROR)) {
                return ERROR;
            }

            for (int j = i + 1; j < os.size(); ++j) {
                int sj = os.get(j);
                if (sj > si) {
                    os.set(j, sj - 1);
                }
            }

            nsf.add(si, r);
        }

        LongFraction r = nsf.get(0);
        if (r.denom != 1) {
            return ERROR;
        }

        return (int) r.numer;
    }

    private LongFraction count(LongFraction a, LongFraction b, int s) {
        switch (s) {
            case 0:
                return a.add(b);
            case 1:
                return a.subtract(b);
            case 2:
                return a.multiply(b);
            case 3:
                return (b.equals(LongFraction.ZERO) ? FERROR : a.divide(b));
        }
        return FERROR;
    }

    private ArrayList<Integer> makeList(int... ns) {
        ArrayList<Integer> r = new ArrayList<Integer>();
        for (int n : ns) {
            r.add(n);
        }
        return r;
    }
}
