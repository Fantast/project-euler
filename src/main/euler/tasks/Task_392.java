package tasks;

import utils.log.Logger;

import java.util.Random;

import static java.lang.Math.*;

//Answer :
public class Task_392 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_392());
        Logger.close();
    }

//    int NN = 2;
    int NN = 10;
    int N = NN/2;
    int N2 = N /2;

    double a[] = new double[N + 2];
    Random rand = new Random();

    public void solving() {
        a[0] = 0;
        for (int i = 1; i <= N; ++i) {
            a[i] = i * PI/2 / (N+1);
        }
        a[N+1] = PI/2;

        double res = count();
        System.out.println(res*4);


        System.out.println();
        for (int i = 0; i < a.length; i++) {
            double v = a[i];
            System.out.println(v/PI*180);

        }
        System.out.println();


        double da = 1e-3;
        for (int st = 0; st < 1000000; ++st) {
            if (st%100 == 0) {
                da = 1.0 / pow(10, rand.nextInt(7) + 1);
            }
//            for (int n = 1; n <= N; ++n) {
                int n = rand.nextInt(N) + 1;
                boolean up = rand.nextBoolean();

                if (!up) {
                    if (a[n]+da < a[n+1]) {
                        a[n] += da;
                    }
                } else {
                    if (a[n]-da > a[n-1]) {
                        a[n] -= da;
                    }
                }
//            }

            double nres = count();
            if (nres < res) {
                res = nres;
            }
            System.out.println(st + ":" + res*4);
        }

        System.out.println();
        for (int i = 0; i < a.length; i++) {
            double v = a[i];
            System.out.println(v/PI*180);

        }
        System.out.println();
        System.out.println(res*4);
    }

    private double count() {
        double res = 0;
        for (int i = 1; i <= N+1; ++i) {
            res += cos(a[i-1]) * (sin(a[i]) - sin(a[i-1]));
        }

        return res;
    }
}
