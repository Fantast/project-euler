package opener;

import tasks.ITask;
import tasks.Tester;
import utils.log.Logger;

//Answer :
public class Task_15 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_15());
        Logger.close();
    }

    int a1 = 1 << 29;
    int a2 = a1 + 63;

    public void solving() {
        System.out.println(a1);
        int mx = 0;
        for (int a = a1; a <= a2; ++a) {
            System.out.println("-------------");
            System.out.println(a);
            int sum = 0;
            for (int b = 1; b <= a/2; ++b) {
//                if (b%2 != 0 && func6(a, b)) {
                if (b%2 != 0 && a%b ==0) {
                    sum += b;
                }
            }
            if (a%2 != 0) {
                sum = a + sum;
            }
            System.out.println(sum);
            if (mx < sum) {
                mx = sum;
            }
        }
        System.out.println(mx);
    }
}
