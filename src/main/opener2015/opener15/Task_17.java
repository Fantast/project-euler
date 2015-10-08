package opener15;

import tasks.AbstractTask;
import tasks.Tester;
import utils.log.Logger;

import java.util.Arrays;
import java.util.StringJoiner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//Answer :
public class Task_17 extends AbstractTask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_17());
        Logger.close();
    }

    public void solving() {
        int rr = randomInt(13) + 1; //1..13
        int rr2 = rr*2;
        int rn = rr << 1;

        int a[] = new int[rr2];
        String ch[] = new String[rr2];


        int opened = 0; //..brackets
        for (int i = 0; i < rr2; i++) {
            boolean open;
            if (opened == 0) {
                open = true;
            } else if (opened == rr2 - i) {
                open = false;
            } else {
                open = randomBoolean();
            }
            a[i] = open ? 1 : 0;
            ch[i] = open ? "(" : ")";
            opened += open ? 1 : -1;
        }
        String result = Arrays.stream(ch)
                .map(i -> (""+i))
                .collect(Collectors.joining(""));
        System.out.println(result);
    }

    public char[] ws(int n) {
        return new char[n];
    }
}
