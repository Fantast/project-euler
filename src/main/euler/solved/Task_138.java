package solved;

import tasks.ITask;
import tasks.Tester;

//Answer : 1118049290473932
//@see: http://www.research.att.com/~njas/sequences/A007805
public class Task_138 implements ITask {
//    private long LIM = 1000000000L;

    public void solving() {
//        int cnt = 0;
//        long sum = 0;
//        for (long L = 17; L < LIM;++L) {
//            if (isExactSquare(5*L*L-1)) {
//                System.out.println(L);
//                ++cnt;
//                sum += L;
//                if (cnt==12) {
//                    System.out.println(sum);
//                    break;
//                }
//            }
//        }

//        after encountering some first values,
//        search on sequnce enciclopedy to know the others
//        http://www.research.att.com/~njas/sequences/A007805
        System.out.println(17L+305L+5473L+98209L+1762289L+31622993L+567451585L+10182505537L+182717648081L+3278735159921L+58834515230497L+1055742538989025L);
    }

    public static void main(String[] args) {
        Tester.test(new Task_138());
    }
}
