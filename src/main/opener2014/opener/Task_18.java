package opener;

import tasks.ITask;
import tasks.Tester;
import utils.OtherUtils;
import utils.log.Logger;

import java.util.*;

import static java.util.Arrays.asList;

//Answer :
public class Task_18 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_18());
        Logger.close();
    }

    public void solving() {
        int first = 122;
        List<Integer> g = asList(
                84, 104, 101, 32, 97, 110, 99, 105, 101, 110, 116, 32, 112, 104, 105, 108, 111, 115, 111,
                134, 101, 114, 115, 32, 114, 126, 107, 101, 100, 32, 99, 111, 137, 117, 143, 125, 109, 111, 110,
                103, 32, 116, 123, 32, 98, 111, 100, 129, 144, 111, 102, 32, 119, 135, 99, 104, 173, 124, 107, 110,
                111, 119, 32, 159, 108, 121, 162, 164, 110, 97, 109, 101, 115, 46, 32, 87, 123, 110, 188, 101, 187,
                119, 142, 124, 97, 115, 148, 100, 44, 32, 102, 111, 114, 32, 101, 120, 191, 112, 108, 101, 210, 174, 187, 115,
                117, 176, 125, 165, 167, 202, 206, 145, 149, 210, 163, 201, 125, 110, 115, 203, 114, 234, 32, 105, 132, 119,
                232, 105, 199, 118, 105, 114, 116, 117, 124, 171, 228, 113, 117, 97, 108, 245, 202, 175, 177, 109, 97, 100,
                124, 245, 125, 112, 112, 101, 97, 214, 242, 100, 195, 89, 111, 117, 32, 109, 117, 115, 132, 98, 124, 115,
                130, 115, 105, 98, 220, 188, 97, 132, 225, 227, 126, 238, 240, 142, 151, 159, 118, 201, 144, 181, 244, 110,
                212, 114, 267, 116, 105, 159, 210, 126, 150, 163, 298, 244, 246, 282, 108, 150, 104, 97, 308, 165, 101, 130,
                32, 259, 245, 205, 144, 285, 227, 116, 111, 200, 133, 155, 112, 138, 124, 343, 306, 313, 193, 144, 105, 103,
                181, 146, 127, 101, 46
        );
        
//        compress(
//                asList(84, 104, 101, 32, 97, 110, 99, 105, 101, 110, 116, 32, 112, 104, 105, 108, 111, 115, 111,
//                       112, 104, 101, 114, 115, 32, 114, 97, 110,
//                       121), true
//        );
        List<Integer> data = guess(first, g);
        compress(data, false);
        
        String s = "";
        int sum = 0;
        for (int e : data) {
            s += (char)e;
            sum += e;
        }
        System.out.println(s);
        System.out.println(sum);
    }

    private List<Integer> guess(int size, List<Integer> g) {
        
        Map<List<Integer>, List<Integer>> entries = new LinkedHashMap<List<Integer>, List<Integer>>();
        List<List<Integer>> keys = new ArrayList<List<Integer>>();
        List<List<Integer>> vals = new ArrayList<List<Integer>>();
        for (int i = 0; i < size; ++i) {
            entries.put(asList(i), asList(i));
            keys.add(asList(i));
            vals.add(asList(i));
        }

        List<Integer> result = newArrayList(size);
        List<Integer> sequence = new ArrayList<Integer>();
        List<Integer> data = new ArrayList<Integer>();

        int curr = 0;
        int dcurr = 0;
        while (curr < g.size()) {
            while (curr < g.size() && vals.contains(asList(g.get(curr)))) {
                data.addAll(keys.get(g.get(curr++)));
            }
            while (dcurr < data.size()) {
                int e = data.get(dcurr);
                List<Integer> tmp = newArrayList(sequence, e);
                if (entries.containsKey(tmp)) {
                    sequence = tmp;
                } else {
                    result = newArrayList(result, entries.get(sequence));
                    entries.put(tmp, newArrayList(size));
                    keys.add(tmp);
                    vals.add(newArrayList(size));
                    
                    sequence = newArrayList(e);
                    ++size;
                    
                    if (curr < g.size() && size-1 == g.get(curr)) {
                        ++dcurr;
                        break;
                    }
                }
                ++dcurr;
            }
        }

        result = newArrayList(result, entries.get(sequence));
//        System.out.println(result);
        System.out.println(data);
        return data;
    }
    
    private List<Integer> compress(List<Integer> data, boolean print) {
        int size = Collections.max(data) + 1;

        Map<List<Integer>, List<Integer>> entries = new LinkedHashMap<List<Integer>, List<Integer>>();
        List<List<Integer>> keys = new ArrayList<List<Integer>>();
        List<List<Integer>> vals = new ArrayList<List<Integer>>();
        for (int i = 0; i < size; ++i) {
            entries.put(asList(i), asList(i));
            keys.add(asList(i));
            vals.add(asList(i));
        }

        List<Integer> result = newArrayList(size);
        List<Integer> sequence = new ArrayList<Integer>();

        for (int e : data) {
            List<Integer> tmp = newArrayList(sequence, e);
            if (entries.containsKey(tmp)) {
                sequence = tmp;
            } else {
                result = newArrayList(result, entries.get(sequence));
                entries.put(tmp, newArrayList(size));
                keys.add(tmp);
                vals.add(newArrayList(size));
                sequence = newArrayList(e);
                ++size;
            }
        }

        result = newArrayList(result, entries.get(sequence));
        if (print) {
            for (int i = 0; i < size; ) {
                for (int j = 0; j < 10 && i < keys.size(); ++j, ++i) {
                    System.out.print(OtherUtils.toIndentedString(i) + ": " + keys.get(i) + "->" + vals.get(i) + ";  ");
                }
                System.out.println();
            }
        }
        System.out.println(result);
        return result;
    }

    private List<Integer> newArrayList(List<Integer> s1, List<Integer> s2) {
        List<Integer> r = new ArrayList<Integer>(s1);
        r.addAll(s2);
        return r;
    }

    private List<Integer> newArrayList(List<Integer> sequence, int e) {
        List<Integer> r = new ArrayList<Integer>(sequence);
        r.add(e);
        return r;
    }

    private List<Integer> newArrayList(int n) {
        List<Integer> r = new ArrayList<Integer>();
        r.add(n);
        return r;
    }
}
