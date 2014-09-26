package tasks;

import static utils.MyMath.sumOfDigits;

//Answer :
public class Task_254 implements ITask {
    int sm[] = new int[151];
    long fact[] = new long[10];

    public void solving() {
        fact[0] = 1;
        for (int i = 1; i < 10; ++i) {
            fact[i] = fact[i-1] * i;
        }

        
        
        for (int i = 1; i < 10000000; ++i) {
            int s = sumOfDigits(sumOfFactorialsDigits(i));
            if (s < sm.length && sm[s] == 0) {
                sm[s] = i;
            }
        }

        long res = 0;
        for (int i = 1; i <= 150; ++i) {
            if (sm[i] == 0) {
//                System.out.println("Fuck: " + i);
            } else {
                long smf = sumOfFactorialsDigits(sm[i]);
                System.out.println(i + ": " + sm[i] + " " + smf);
            }
            res += sumOfDigits(sm[i]);
        }
        System.out.println(res);
    }

    private long sumOfFactorialsDigits(int n) {
        long sum = 0;
        while (n != 0) {
            sum += fact[n%10];
            n /= 10;
        }

        return sum;
    }

    public static void main(String[] args) {
        Tester.test(new Task_254());
    }
}
