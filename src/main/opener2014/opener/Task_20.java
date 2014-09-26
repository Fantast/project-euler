package opener;

import tasks.ITask;
import tasks.Tester;
import utils.FileUtils;
import utils.log.Logger;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.*;

//Answer :
public class Task_20 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_20());
        Logger.close();
    }

    public void solving() throws IOException {

        int group = 0;
        Map<String, Integer> g = new HashMap<String, Integer>();
        Map<Integer, Set<String>> all = new HashMap<Integer, Set<String>>();
        for (int i = 1; i <= 5; ++i) {
            String listName = "d:\\1-task-lists\\list" + i;
            if (!new File(listName).exists()) {
                continue;
            }
            for (String line : FileUtils.readLines(listName)) {
//            for (String line : FileUtils.readLines("d:\\6-task\\sample" + i)) {
                Set<String> members = new HashSet<String>();
                int newGri = group++;

                String[] ss = line.split(";");
                for (String s : ss) {
//                    if (!is.contains(s)) {
//                        continue;
//                    }
                    Integer gri = g.get(s);
                    if (gri != null) {
                        Set<String> oldGroup = all.remove(gri);
                        for (String oldm : oldGroup) {
                            g.remove(oldm);
                        }
                        members.addAll(oldGroup);
                    }
                    members.add(s);
                }

                for (String m : members) {
                    g.put(m, newGri);
                }

                if (!members.isEmpty()) {
                    all.put(newGri, members);
                }
            }
        }

        System.out.println(all.size());

        for (Set<String> k : all.values()) {
            if (k.size() == 3) {
                BigInteger p = BigInteger.ONE;
                for (String sn : k) {
                    p = p.multiply(new BigInteger(sn, 36));
                }

                System.out.println(p.toString(36));
            }
        }
    }

    public void solving1() throws Exception {
        String s = "16KRXKH3I.mov CKHBWPBMT.mov QIQUK3L4B.mov ";
        s = s.replaceAll("\\.mov", "");

        String ss[] = s.split(" ");
        BigInteger p = BigInteger.ONE;
        for (String sn : ss) {
            p = p.multiply(new BigInteger(sn, 36));
        }

        System.out.println(p.toString(36));
    }
}
