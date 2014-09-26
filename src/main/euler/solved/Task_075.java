package solved;

import tasks.ITask;
import tasks.Tester;

//Answer : 161667
public class Task_075 implements ITask {
    public void solving() {
        long res = 0;

        for (int A = 12; A <= 1000000; A+=2) {
            int k = 0;
            for (int b = 1; b < A/2; ++b) {
//                int t = (A/2*(A-2*b));
                if ( (A/2*(A-2*b)) % (A-b) == 0 ) {
                    int a = (A/2*(A-2*b)) / (A-b);
                    int c = A-a-b;
                    if (a+b<=c || a+c<=b || b+c<=a) continue;
                    if (b>a) break;

                    ++k;
                    if (k > 1) break;
                }
            }
            if (k == 1) {
//                System.out.println(A);
                ++res;
            }
            if (A%100000 == 0) {
                System.out.println(A);
            }
        }

        System.out.println( res );
    }

    public static void main(String[] args) {
        Tester.test(new Task_075());
    }
}
