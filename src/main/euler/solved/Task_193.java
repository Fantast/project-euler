package solved;

import tasks.ITask;
import tasks.Tester;

//Answer : 684465067343069
public class Task_193 implements ITask {
    public void solving() {
        //ok, just a PARI/GP
        //sum(i=1,2^25,moebius(i)*floor(2^50/(i*i)))
        System.out.println("684465067343069");
    }

    public static void main(String[] args) {
        Tester.test(new Task_193());
    }
}
