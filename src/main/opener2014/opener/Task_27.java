package opener;

import tasks.ITask;
import tasks.Tester;
import utils.IntPoint;
import utils.log.Logger;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

import static java.lang.Math.max;

//Answer :
public class Task_27 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_27());
        Logger.close();
    }

    int moves = 2081560;
    //    int moves = 500;
    int xn = 2500;
    int yn = 2500;
    boolean b[][] = new boolean[xn][yn];
    int minx;
    int maxx;
    int miny;
    int maxy;
    int szx;
    int szy;

    public void solving() throws IOException {
        int x = 0;
        int y = 0;
        int dir = 0;
        for (int steps = 0; steps < 2; ++steps) {
            BufferedReader in = new BufferedReader(new FileReader("D:\\Downloads\\robot.log"));

            dir = 0;
            x = maxx = minx = max(minx, -minx);
            y = maxy = miny = max(miny, -miny);

            for (int m = 0; m < moves; ++m) {
                if (steps == 1) {
                    b[x][y] = true;
                }

                char mv = (char) in.read();
                skipFully(in, 3);

                boolean minus = in.read() != '+';

                int cnt = in.read() - '0';

                skipFully(in, 1);

                if (mv == 'S') {
                    mv = 'M';
                    dir = (dir + 2) % 4;
                    if (!minus) {
                    }
                }
                if (mv == 'M') {
                    if (cnt != 1) {
                        System.out.println("eh>");
                    }
                    switch (dir) {
                        case 0: ++x; break;
                        case 1: ++y; break;
                        case 2: --x; break;
                        case 3: --y; break;
                    }
                }
                if (mv == 'T') {
                    dir = (dir + 4 + (minus ? -cnt : cnt)) % 4;
                }

                minx = Math.min(minx, x);
                maxx = max(maxx, x);
                miny = Math.min(miny, y);
                maxy = max(maxy, y);
            }
            szx = (maxx - minx + 1);
            szy = (maxy - miny + 1);
            System.out.println(szx + " " + szy);

            in.close();
        }


        System.out.println(minx + " " + miny);
        System.out.println(maxx + " " + maxy);
        System.out.println(szx + " " + szy);

        generatePicture();

        int res = 0;
        for (x = minx; x <= maxx; ++x) {
            for (y = miny; y <= maxy; ++y) {
//                res += find(x, y);
                if (find(x, y) != 0) {
                    ++res;
                }
            }
        }
        System.out.println(res);
    }

    private void generatePicture() throws IOException {
        BufferedImage img = new BufferedImage(szx, szy, BufferedImage.TYPE_INT_RGB);
        for (int x = minx; x <= maxx; ++x) {
            for (int y = miny; y <= maxy; ++y) {
                img.setRGB(x - minx, y - miny,
                           (b[x][y]
                            ? Color.black
                            : Color.lightGray).getRGB()
                );
            }
        }

        File f = new File("D:\\Downloads\\robot.bmp");
        ImageIO.write(img, "BMP", f);
        System.out.println("Picture saved!");
    }

    Queue<IntPoint> q = new LinkedList<>();

    private int find(int X, int Y) {
        if (b[X][Y]) {
            return 0;
        }

        boolean wall = false;
        q.add(new IntPoint(X, Y));
        b[X][Y] = true;
        int cnt = 1;
        while (!q.isEmpty()) {
            IntPoint curr = q.poll();
            int x = curr.x;
            int y = curr.y;
            ++cnt;

            if (x == minx || y == miny || x == maxx || y == maxy) {
                wall = true;
            }

//            for (int dx = -1; dx <=1; ++ dx) {
//                for (int dy = -1; dy <=1; ++ dy) {
////                    if (dx != 0 || dy != 0) {
//                    if (dx == 0 || dy == 0) {
//                        int nx = x + dx;
//                        int ny = y + dy;
//                        if (nx >= minx && nx <= maxx && ny >= miny && ny <= maxy && !b[nx][ny]) {
//                            b[nx][ny] = true;
//                            q.add(new IntPoint(nx, ny));
//                        }
//                    }
//                }
//            }

            if (x > minx && !b[x - 1][y]) {
                b[x - 1][y] = true;
                q.add(new IntPoint(x - 1, y));
            }
            if (x < maxx && !b[x + 1][y]) {
                b[x + 1][y] = true;
                q.add(new IntPoint(x + 1, y));
            }
            if (y > miny && !b[x][y - 1]) {
                b[x][y - 1] = true;
                q.add(new IntPoint(x, y - 1));
            }
            if (y < maxy && !b[x][y + 1]) {
                b[x][y + 1] = true;
                q.add(new IntPoint(x, y + 1));
            }
        }

        return wall ? 0 : cnt;
    }

    private void skipFully(BufferedReader in, int n) throws IOException {
        if (in.skip(n) != n) {
            throw new IllegalStateException("eh..");
        }
    }
}
