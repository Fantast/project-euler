package solved;

import tasks.ITask;
import tasks.Tester;
import utils.log.Logger;

//Answer: 608720
public class Task_145 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_145());
        Logger.close();
    }

    public void solving() {
        int res = 0;

        for (int i = 0; i < 1000000000; ++i) {
            if (i%10000000 == 0) {
                System.out.println(i);
            }
            if ( isOk(i) ) {
                res += 2;
//                System.out.println(i);
            }
        }

        System.out.println(res);
    }

    private boolean isOk(int i) {
        if (i%10 == 0) return false;
        int j = Integer.parseInt(new StringBuilder(""+i).reverse().toString());
        if (j >= i) return false;

        String s = "" + (j + i);
        for (int k = 0; k < s.length(); ++k) {
            int d = s.charAt(k) - '0';
            if (d%2==0) return false;
        }

        return true;
    }
}
