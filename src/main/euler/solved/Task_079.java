package solved;

import tasks.ITask;

import java.util.Arrays;

import static utils.OtherUtils.arrayToString;

//Answer : 73162890
public class Task_079 implements ITask {
    int attempts[] = new int[] {
        319, 680, 180, 690, 129, 620, 762, 689, 762, 318, 368, 710, 720, 710, 629, 168, 160, 689, 716, 731, 736, 729, 316,
        729, 729, 710, 769, 290, 719, 680, 318, 389, 162, 289, 162, 718, 729, 319, 790, 680, 890, 362, 319, 760, 316, 729,
        380, 319, 728, 716
    };

    public void solving() {
        Arrays.sort(attempts);

        int n = attempts.length;
        System.out.println(n);
        System.out.println( arrayToString(attempts) );

        int len = 0;
        for (int i = 0; i < n; ++i) {
            attempts[len++] = attempts[i];
            while (i < n-1 && attempts[i] == attempts[i+1]) ++i;
        }

        System.out.println(len);
        System.out.println( arrayToString(attempts, 0, len) );

        // just see - the answer is 73162890
    }
}
