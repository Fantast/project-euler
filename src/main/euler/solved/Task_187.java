package solved;

import tasks.ITask;
import tasks.Tester;
import utils.log.Logger;

//Answer : 17427258
public class Task_187 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_187());
        Logger.close();
    }

    private static final int MAX = 100000000;

    int first[] = new int[MAX];
    boolean isNotPr[] = new boolean[MAX];

    public void solving() {
        long res = 0;

//        if (2*2 <= MAX) ++res;
        ++res;
        for (int j = 4; j < MAX; j += 2) {
            isNotPr[j] = true;
            first[j] = 2;
        }


        int sqM = (int) Math.sqrt(MAX);
        for (int i = 3; i < MAX / 2; i += 2) {
            if (!isNotPr[i]) {
//                if (i*i <= MAX) ++res;
                if (i <= sqM) ++res;

                for (int j = i+i; j < MAX; j += i) {
                    isNotPr[j] = true;

                    if (first[j] != 0) {
                        if (first[j] == j / i) {
//                            System.out.println(j);
                            ++res;
                        }
                    } else {
                        first[j] = i;
                    }
                }
            }
        }

        System.out.println( res );
    }
}