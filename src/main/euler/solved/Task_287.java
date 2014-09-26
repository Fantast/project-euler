package solved;

import tasks.ITask;
import tasks.Tester;
import utils.log.Logger;

import static utils.MyMath.pow;

//Answer : 313135496
public class Task_287 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_287());
        Logger.close();
    }

    int n = 24;
    public final long n2 = 1L << (n - 1);
    public final long n22 = 1L << (2 * n - 2);
    int sz = (1 << n);

    public void solving() {
        System.out.println(findColorStringLength(0, 0, 1 << n));
    }

    private long findColorStringLength(int x, int y, int size) {
        int sz2 = size / 2;
        return size != sz && (size==1 || monochrome(x, y, size))
               ? 2
               : 1
                 + findColorStringLength(x, y, sz2)
                 + findColorStringLength(x, y + sz2, sz2)
                 + findColorStringLength(x + sz2, y, sz2)
                 + findColorStringLength(x + sz2, y + sz2, sz2);
    }

    private boolean monochrome(int x, int y, int size) {
        boolean white = isWhite(x, y);
        --size;
        return white == isWhite(x + size, y)
                 && white == isWhite(x + size, y + size)
                 && white == isWhite(x, y + size);
    }

    private boolean isWhite(long x, long y) {
        return pow(y - n2, 2) + pow(x - n2, 2) > n22;
    }
}
