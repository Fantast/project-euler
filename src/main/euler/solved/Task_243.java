package solved;

import tasks.ITask;
import tasks.Tester;

import static utils.MyMath.getPrimesBetween;
import static utils.MyMath.phi;

//Answer : 892371480
public class Task_243 implements ITask {
    public void solving() {
        long prs[] = getPrimesBetween(1, 50);
        int n = prs.length;

        long primorial = 1;
        for (int i = 0; i < n; ++i) {
            primorial *= prs[i];
            for (int j = 1; primorial * j < primorial*prs[i+1]; ++j) {
                if (phi(primorial * j) * 94744L < (primorial * j-1) * 15499L) {
                    System.out.println(primorial * j);
                    return;
                }
            }
        }
    }

    public static void main(String[] args) {
        Tester.test(new Task_243());
    }
}
