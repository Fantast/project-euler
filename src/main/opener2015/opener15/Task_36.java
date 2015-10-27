package opener15;

import tasks.AbstractTask;
import tasks.Tester;
import utils.log.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

//Answer :
public class Task_36 extends AbstractTask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_36());
        Logger.close();
    }

    public static final Comparator<List<Integer>> listComparator = new Comparator<List<Integer>>() {
        @Override
        public int compare(List<Integer> o1, List<Integer> o2) {
            for (int i = 0; i < o1.size(); ++i) {
                if (o1.get(i) < o2.get(i)) {
                    return -1;
                }
                if (o1.get(i) > o2.get(i)) {
                    return 1;
                }
            }
            return 0;
        }
    };

    int given[] = new int[] {1,33,27,44,1,33,18,44,7,46,1,44,1,100,1,119,1,100,1,119,2,100,1,119,4,100,1,119,1,116,2,100,1,119,8,115,12,100,5,101,2,97,8,101,1,97,1,101,1,97,2,101,21,111,1,102,1,101,2,97,1,115,7,108,4,101,1,97,1,101,1,97,8,73,1,97,12,101,1,97,2,101,2,97,1,101,1,100,6,101,2,97,1,119,12,100,8,116,1,44,8,110,1,116,1,119,7,121,7,115,6,101,1,97,5,101,6,111,1,101,3,111,1,101,3,111,28,101,5,100,4,104,4,100,3,104,4,100,8,104,8,100,1,104,6,100,5,104,1,114,6,100,4,116,3,103,2,116,6,114,1,119,5,100,4,33,8,101,8,110,7,119,8,100,6,116,1,101,1,119,1,100,1,116,1,103,1,116,8,101,8,110,1,100,1,119,7,121,2,116,7,121,5,100,4,116,3,103,2,116,6,114,1,101,6,114,1,103,1,100,7,101,50,10,1,0,4,10,16,32,1,101,1,109,41,119,8,32,8,109,12,32,7,104,8,119,1,104,4,99,2,111,7,104,1,111,1,99,1,111,4,99,2,111,21,99,11,32,1,97,30,32,21,116,6,105,4,32,29,101,6,110,27,101,6,110,5,114,8,108,7,114,1,97,6,105,1,32,12,105,22,32,37,104,6,100,40,104,1,110,8,114,1,115,8,104,1,115,7,105,1,100,10,119,12,108,1,110,6,108,27,119,1,112,1,104,6,100,6,104,6,100,8,104,7,80,1,111,14,32,8,111,12,105,1,97,12,103,5,32,21,99,7,114,1,87,6,84,49,116,2,115,13,83,6,115,8,83,7,115,1,83,7,32,8,84,8,119,3,32,1,116,7,119,6,116,6,115,12,112,7,100,6,114,6,106,1,103,6,32,11,98,6,32,6,99,8,32,7,108,8,111,12,103,6,107,7,39,41,97,41,108,14,102,1,73,8,111,16,97,8,111,12,97,1,105,1,101,8,107,6,105,25,116,8,104,2,103,1,114,3,103,1,32,3,100,1,104,4,100,8,32,8,119,8,100,1,32,1,104,1,99,4,108,1,72,1,110,1,104,1,99,7,110,2,99,37,108,1,111,12,115,7,97,19,101,5,105,1,117,6,105,15,101,6,119,1,104,1,117,1,111,8,97,1,39,7,112,2,114,15,32,6,110,12,32,1,117,1,98,41,32,1,97,8,39,1,115,43,97,81,32,1,115,1,111,1,74,17,111,41,115,8,32,37,111,29,32,7,104,14,108};

    public void solving() {
        List<Integer> seq = new ArrayList<>();
        List<Integer> seq2 = new ArrayList<>();
        int all = 0;
        for (int i = 0; i < given.length/2; i+=2) {
            int c = given[i];
            int e = given[i+1];
            all += c;
            for (int j = 0; j < c; ++j) {
                seq.add(e);
                seq2.add(e);
            }
        }
        Collections.sort(seq);
        for (int i = 0; i < seq.size(); i++) {
            System.out.println(seq.get(i) + " " + seq2.get(i));
        }
        System.out.println(all);
//        System.out.println(seq);
//        System.out.println(seq2);
    }

    private void encode() {
        int a[] = new int[] {1,1,2,2,4,4,3,3,0};
        int n = a.length;

        List<List<Integer>> all = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            List<Integer> current = new ArrayList<>();
            for (int j = 0; j < n; ++j) {
                current.add(a[(i+j)%n]);
            }
            all.add(current);
        }

        Collections.sort(all, listComparator);
        for (List<Integer> e : all) {
            System.out.println(e);

        }
    }
}
