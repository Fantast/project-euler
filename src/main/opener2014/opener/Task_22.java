package opener;

import tasks.ITask;
import tasks.Tester;
import utils.log.Logger;

import java.io.FileInputStream;
import java.io.IOException;

//Answer :
public class Task_22 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_22());
        Logger.close();
    }

    int n = 15;
    byte[] ds = new byte[n];
    int curr = 0;

    public void solving() throws IOException {
        FileInputStream is = new FileInputStream("d:\\1\\Zeta(3) - Hex - Amdeberhan-Zeiberger 2.txt");
//        FileInputStream is = new FileInputStream("d:\\1\\1.txt");
        System.out.println(is.read());
        System.out.println(is.read());

        int pos = 0;
        int sumreaden = 0;
        int readen;
        byte[] buf = new byte[65535];
        while ((readen = is.read(buf)) != -1) {
            sumreaden += readen;
            for (int i = 0; i < readen; ++i) {
                ds[curr] = buf[i];
                if (isPalindrom()) {
                    System.out.println(pos - n+1);
                    return;
                }
                ++pos;
                curr = (curr + 1) % n;
            }
        }
        System.out.println("Not found ((");
        System.out.println(sumreaden);
        System.out.println(pos);
    }

    private boolean isPalindrom() {
        int i1 = curr;
        int i2 = (curr + 1) % n;
        while (i1 != i2) {
            if (ds[i1] != ds[i2]) {
                return false;
            }
            i1 = (i1 + n-1) % n;
            i2 = (i2 + 1) % n;
        }
        return true;
    }
}
