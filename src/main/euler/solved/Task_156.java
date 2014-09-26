package solved;

import tasks.ITask;
import tasks.Tester;
import utils.log.Logger;

//Answer : 21295121502550
public class Task_156 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_156());
        Logger.close();
    }

    public void solving() {
        long cnt[] = new long[10];
        long res = 0;

        long begTime = System.currentTimeMillis();
        for (int d1 = 0; d1 < 10; ++d1) {
            System.out.println("Progress: " + d1 + " in " + (System.currentTimeMillis() - begTime) + " ms.");
            long n1 = d1 * 1000000000L;
            for (int d2 = 0; d2 < 10; ++d2) {
                long n2 = n1 + d2 * 100000000L;
                for (int d3 = 0; d3 < 10; ++d3) {
                    long n3 = n2 + d3 * 10000000L;
                    for (int d4 = 0; d4 < 10; ++d4) {
                        long n4 = n3 + d4 * 1000000L;
                        for (int d5 = 0; d5 < 10; ++d5) {
                            long n5 = n4 + d5 * 100000L;
                            for (int d6 = 0; d6 < 10; ++d6) {
                                long n6 = n5 + d6 * 10000L;
                                for (int d7 = 0; d7 < 10; ++d7) {
                                    long n7 = n6 + d7 * 1000L;
                                    for (int d8 = 0; d8 < 10; ++d8) {
                                        long n8 = n7 + d8 * 100L;
                                        for (int d9 = 0; d9 < 10; ++d9) {
                                            long n9 = n8 + d9 * 10L;
                                            for (int d10 = 0; d10 < 10; ++d10) {
                                                long n10 = n9 + d10;
                                                cnt[d1]++;
                                                cnt[d2]++;
                                                cnt[d3]++;
                                                cnt[d4]++;
                                                cnt[d5]++;
                                                cnt[d6]++;
                                                cnt[d7]++;
                                                cnt[d8]++;
                                                cnt[d9]++;
                                                cnt[d10]++;
                                                for (int i = 1; i <= 9; ++i) {
                                                    if (cnt[i] == n10) {
                                                        long last = 10000000000L * (i - 1);
                                                        for (long base = 0; base <= last; base += 10000000000L) {
                                                            long curr = base + n10;

                                                            res += curr;
                                                            System.out.println(i + ": " + curr);
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        System.out.println(res);
    }
}
