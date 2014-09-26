package solved;

import tasks.ITask;
import tasks.Tester;

import java.util.*;

import static java.lang.Math.max;
import static utils.MyMath.isExactSquare;

//Answer : 18769
public class Task_098 implements ITask {

    String words[] = {
        "A", "ABILITY", "ABLE" // other words deleted
    };

    long max = 0;
    public void solving() {
        TreeMap<String, ArrayList<String>> grs = new TreeMap<String, ArrayList<String>>();
        for (String word : words) {
            String key = getCanonicalAnagramm(word);
            
            ArrayList<String> ws = grs.get(key);
            if (ws == null) {
                ws = new ArrayList<String>();
                grs.put(key, ws);
            }
            ws.add(word);
        }

        for (Iterator<Map.Entry<String, ArrayList<String>>> it = grs.entrySet().iterator(); it.hasNext(); ) {
            Map.Entry<String, ArrayList<String>> entry = it.next();

            if (entry.getValue().size() < 2) {
                it.remove();
            }
        }

        for (Map.Entry<String, ArrayList<String>> entry : grs.entrySet()) {
            solve(entry.getKey(), entry.getValue().toArray(new String[0]));
        }

        System.out.println(max);
    }

    private String getCanonicalAnagramm(String w) {
        char chs[] = w.toCharArray();
        Arrays.sort(chs);
        return new String(chs);
    }

    public static void main(String[] args) {
        Tester.test(new Task_098());
    }

    private void solve(String canonical, String[] ws) {
        int depth = 0;
        for (char ch = 'A'; ch <= 'Z'; ++ch) {
            if (canonical.contains("" + ch)) {
                ++depth;
            }
        }

        fill(depth, 0, new int[20], canonical, ws);
    }

    boolean used[] = new boolean[10];
    private void fill(int depth, int ind, int ds[], String canonical, String[] ws) {
        if (ind == depth) {
            process(ds, canonical, ws);
            return;
        }

        for (int d = 0; d <= 9; ++d) {
            if (used[d]) continue;
            used[d] = true;
            ds[ind] = d;
            fill(depth, ind+1, ds, canonical, ws);
            used[d] = false;
        }
    }

    private void process(int[] ds, String can, String[] ws) {
        String ws2[] = ws.clone();
        
        int ind = 0;
        for (int i = 0; i < can.length(); ++i) {
            char ch = can.charAt(i);
            if (Character.isLetter(ch)) {
                char dc = (char) ('0' + ds[ind++]);

                can = can.replace(ch, dc);
                for (int j = 0; j < ws2.length; ++j) {
                    ws2[j] = ws2[j].replace(ch, dc);
                }
            }
        }

        long maxs = 0;
        int cnts = 0;
        for (String ns : ws2) {
            if (ns.charAt(0) == '0') return;
            
            long n = Long.parseLong(ns);
            if (isExactSquare(n)) {
                cnts++;
                maxs = max(maxs, n);
            }
        }

        if (cnts >1) max = max(max, maxs);
    }
}
