package opener15;

import tasks.AbstractTask;
import tasks.Tester;
import utils.log.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//Answer : 7080160075
//used with prime sieve from: http://primesieve.org/
//./primesieve 100000000000 -p | java -cp classes opener15.Task_14
public class Task_14 extends AbstractTask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_14());
        Logger.close();
    }

    public void solving() throws IOException {

        BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

        char ich = 0;
        char pch = 0;
        char ach = 0;
        char rch = 0;
        char tch = 0;
        char ych = 0;

        long cnt = 0;
        long prev = 0;
        long pcnt = 0;

        String line;
        TOP:
        while ((line = bfr.readLine()) != null) {
            pcnt++;
            long number = Long.parseLong(line);
            char[] chars = Long.toString(number, 36).toCharArray();

            progress10000000(pcnt);

            for (char ch : chars) {
                ich = pch;
                pch = ach;
                ach = rch;
                rch = tch;
                tch = ych;
                ych = ch;
                ++cnt;
                if (ich == 'i' && pch == 'p' && ach == 'a' && rch == 'r' && tch == 't' && ych == 'y') {
                    System.out.print(Long.toString(prev, 36));
                    System.out.println(Long.toString(number, 36));
                    System.out.println(cnt - 5);
                    break TOP;
                }
            }
            prev = number;
        }
    }
}
