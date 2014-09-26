package tasks;

import utils.log.Logger;

//Answer :  3.237034219788121
public class Task_394 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_394());
        Logger.close();
    }

//    public double d = 1;
//    public double d = 2;
//    public double d = 7.5;
    public double d = 40;

    public long n = 100000000000L;

    public long zn = (long) (n/d);

    public void solving() {
//        double fk = 1;
//        double fs = 0;
//        long begk = zn + 1;
//        Progress: 99800000000 3.2356995732252583 3.551079722910302E-11
//        Progress: 99900000000 3.2363672297021457 3.548218530894846E-11
//        Progress: 100000000000 3.237034219781455 3.54536234266475E-11
        double fk = 2.3716790792574876;
        double fs = 9.572092815021443E-11;
        long begk = 27300000000L;
        for (long k = begk; k <= n; ++k) {
            if (k % 100000000 == 0) {
                System.out.println("Progress: " + k + " " + fk + " " + fs);
            }
//            fk = (fk*(k2 - 2*k + 2) +  (2*fs + 1) + 2*zn)/k2;
//            fs = fs + fk + 1;
            fk = fk/k*(k-1)/k*(k-1) + (fk + 2.0*zn + 1)/k/k + 2*fs;
            fs = fs/(k+1)*k/(k+1)*k + (fk + 1)/(k+1)/(k+1);
        }
        System.out.println();
        System.out.println(fk);
    }
}
