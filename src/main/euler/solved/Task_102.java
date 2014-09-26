package solved;

import tasks.ITask;
import tasks.Tester;
import utils.log.Logger;

import java.awt.*;

//Answer : 228
public class Task_102 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_102());
        Logger.close();
    }

    int[][] trs = {
            {-340,495,-153,-910,835,-947},
            {-175,41,-421,-714,574,-645},
            {-547,712,-352,579,951,-786},
            {419,-864,-83,650,-399,171},
            {-429,-89,-357,-930,296,-29},
//...cut
            {-965,-728,560,569,-708,-247}
    };
    public void solving() {
        int []xp = new int[3];
        int []yp = new int[3];
        long res = 0;
        for (int[] tr : trs) {
            for (int i = 0; i < 3; ++i) {
                xp[i] = tr[2*i];
                yp[i] = tr[2*i+1];
            }

            Shape s = new Polygon(xp, yp, 3);
            if ( s.contains(0, 0) ) res++;
        }

        System.out.println( res );
    }
}
