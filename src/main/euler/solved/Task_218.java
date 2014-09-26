package solved;

import tasks.ITask;
import tasks.Tester;

//Answer(!!!!!!!!!!!!!!!!!) : 0
public class Task_218 implements ITask {

    public void solving() {
//        A = 2*M*N;
//        B = M^2 - N^2;
//        C = M^2 + N^2 = c^2; ==>
//
//        M = 2*m*n;
//        N = m^2 - n^2
//        c = m^2 + n^2;
//
//        S = 1/2 * A * B = M * N * (M^2 - N^2) =
//          = 2*m*n*(m^2 - n^2)*(...)
//
//        http://en.wikipedia.org/wiki/Pythagorean_triples
//        Exactly one of m, n is divisible by 3.
//        So S is divisable by 6, therefore every perfect is superperfect
        System.out.println(0);
    }

    public static void main(String[] args) {
        Tester.test(new Task_218());
    }
}
