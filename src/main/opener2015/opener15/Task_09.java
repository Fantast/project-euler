package opener15;

import tasks.ITask;
import tasks.Tester;
import utils.log.Logger;
import utils.pairs.IntPair;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

//Answer :
public class Task_09 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_09());
        Logger.close();
    }

    int s = 512;
    boolean rr[][] = new boolean[s][s];


    public void solving() throws IOException {

        BufferedImage image = ImageIO.read(new File("/downloads/opener9.png"));

        BufferedImage bwImage = new BufferedImage(s, s, BufferedImage.TYPE_BYTE_BINARY);

        for (int x = 0; x < s; ++x) {
            for (int y = 0; y < s; ++y) {
                boolean isWhite = isWhite(image.getRGB(x * 2 + 1, y * 2 + 1));
                rr[y][x] = isWhite;
                bwImage.setRGB(x, y, isWhite ? 0xFFFFFFFF : 0xFF000000);
            }
        }

        ImageIO.write(bwImage, "png", new File("/downloads/opener9_bw.png"));

        int count = 0;
        for (int x = 0; x < s; ++x) {
            for (int y = 0; y < s; ++y) {
                if (!rr[y][x]) {
                    ++count;
                    fill(x, y);
                }
            }
        }
        System.out.println(count);
    }

    private void fill(int x, int y) {

        Queue<IntPair> q = new LinkedList<>();
        q.add(new IntPair(x, y));
        while (!q.isEmpty()) {
            IntPair curr = q.poll();
            x = curr.a;
            y = curr.b;

            if (y < 0 || y >= s || x < 0 || x >= s || rr[y][x]) {
                continue;
            }

            rr[y][x] = true;
            q.add(new IntPair(x + 1, y));
            q.add(new IntPair(x - 1, y));
            q.add(new IntPair(x, y + 1));
            q.add(new IntPair(x, y - 1));
        }
    }

    private boolean isWhite(int rgb) {
        return rgb != 0xFF000000 && rgb != 0;
    }
}
