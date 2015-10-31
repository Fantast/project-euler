package opener;

import tasks.ITask;
import tasks.Tester;
import utils.FileUtils;
import utils.log.Logger;

import java.io.IOException;
import java.util.*;

//Answer :
public class Task_8 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_8());
        Logger.close();
    }


    int ds[] = new int[100000];
    Map<String, Integer> all = new HashMap<String, Integer>();
    Map<Integer, Set<Integer>> g = new HashMap<Integer, Set<Integer>>();

    public void solving() throws IOException {
        Arrays.fill(ds, -1);

        ArrayList<String> res = FileUtils.readLines("d:\\Downloads\\task.txt");
        for (String r : res) {
            String sp[] = r.split(", ");
            if (sp.length != 2) {
                throw new IllegalStateException("ouch");
            }

            int a = getNum(sp[0]);
            int b = getNum(sp[1]);
            putEdge(a, b);
        }
        //Шерстов Богдан Максович
        System.out.println(all.size());

        int sum = 0;
        for (Map.Entry<Integer, Set<Integer>> e : g.entrySet()) {
            int a = e.getKey();
            sum += count(a);
        }

        System.out.println(sum);
    }

    private int count(int a) {
        int r = ds[a];
        if (r == -1) {
            r = 0;
            Set<Integer> es = g.get(a);
            if (es != null) {
                r += es.size();
                for (int o : es) {
                    r += count(o);
                }
            }
            return ds[a] = r;
        }
        return r;
    }

    private void putEdge(int a, int b) {
        Set<Integer> edges = g.get(a);
        if (edges == null) {
            edges = new HashSet<Integer>();
            g.put(a, edges);
        }
        edges.add(b);
    }

    private int getNum(String s) {
        Integer num = all.get(s);
        if (num == null) {
            num = all.size();
            all.put(s, num);
        }
        return num;
    }
}
