package solved;

import tasks.ITask;
import tasks.Tester;
import utils.Geometry;
import utils.PointD;
import utils.Segment;
import utils.log.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import static java.lang.Math.*;
import static utils.Geometry.EPS;

//Answer : 343047
public class Task_163 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_163());
        Logger.close();
    }

    int s = 100;
    double s32 = s * sqrt(3) / 2;
    double s34 = s * sqrt(3) / 4;
    double s2 = s / 2;
    double s4 = s / 4;

    int n = 36;

    Segment segments[] = new Segment[400];
    int sn = 0;

    public void solving() {
//        Visualizer.run(8);
//        new Visualizer2().run();

        for (int i = 0; i < n; ++i) {
            //parallel to left side
            segments[sn++] = new Segment(i * s, 0, (n + i) * s2, (n - i) * s32);
            //parallel to right side
            segments[sn++] = new Segment((i + 1) * s, 0, (i + 1) * s2, (i + 1) * s32);
            //parallel to bottom side
            segments[sn++] = new Segment(i * s2, i * s32, n * s - i * s2, i * s32);

            //perpendicular to bottom side
            segments[sn++] = new Segment((i + 1) * s2, 0, (i + 1) * s2, (i + 1) * s32);
            if (i != 0) {
                segments[sn++] = new Segment(
                        (n + i) * s2,
                        0,
                        (n + i) * s2,
                        (n - i) * s32);
            }

            //perpendiculars to left side
            segments[sn++] = new Segment((i + 1) * s, 0, (i + 1) * s4, (i + 1) * s34);
            if (i != 0) {
                segments[sn++] = new Segment(
                        n * s - i * s2,
                        i * s32,
                        n * s4 + i * s4,
                        n * s34 + i * s34);
            }

            //perpendiculars to right side
            segments[sn++] = new Segment(i * s, 0, n * s - n * s4 + i * s4, n * s34 - i * s34);
            if (i != 0) {
                segments[sn++] = new Segment(
                        i * s2,
                        i * s32,
                        n * s - n * s4 - i * s4,
                        n * s34 + i * s34);
            }
        }

        int res = 0;
        for (int a = 0; a < sn; ++a) {
            Segment sa = segments[a];
            for (int b = a + 1; b < sn; b++) {
                Segment sb = segments[b];
                for (int c = b + 1; c < sn; c++) {
                    Segment sc = segments[c];
                    PointD ab = Geometry.intersect(sa, sb);
                    PointD ac = Geometry.intersect(sa, sc);
                    PointD bc = Geometry.intersect(sb, sc);
                    if (ab != null && ac != null && bc != null && !equals(ab, ac)) {
                        ++res;
                    }
                }
            }
        }
        System.out.println(res);
    }

    private boolean equals(PointD a, PointD b) {
        return abs(a.x - b.x) < EPS && abs(a.y - b.y) < EPS;
    }

    public class Visualizer2 extends JFrame {
        public void run() {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    Visualizer2 frame = new Visualizer2();
                    frame.setVisible(true);
                }
            });
        }

        public Visualizer2() {
            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            setSize(1224, 1000);

            getContentPane().add(new TrianglesPanel());
        }

        public class TrianglesPanel extends JPanel {
            private BufferedImage img;
            private Graphics2D g2;

            public TrianglesPanel() {
            }

            private void createTrianglesImage() {
                if (g2 != null) {
                    return;
                }

                img = getGraphicsConfiguration().createCompatibleImage(getWidth(), getHeight());
                g2 = img.createGraphics();
                g2.setColor(Color.WHITE);
                g2.fillRect(0, 0, getWidth(), getHeight());
                g2.setColor(Color.BLUE);

                g2.translate(100, 700);
                g2.rotate(-PI);
                g2.translate(-n * s, 0);

                for (int i = 0; i < sn; i++) {
                    Segment s = segments[i];
                    g2.drawLine(s.x1(), s.y1(), s.x2(), s.y2());
                }
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                createTrianglesImage();

                g.drawImage(img, 0, 0, null);
            }
        }
    }

    public static class Visualizer extends JFrame {
        public static void run(final int n) {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    Visualizer frame = new Visualizer(n);
                    frame.setVisible(true);
                }
            });
        }

        public Visualizer(int n) {
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setSize(1224, 1000);

            getContentPane().add(new TrianglesPanel(n));
        }

        public class TrianglesPanel extends JPanel {
            private BufferedImage img;
            private Graphics2D g2;

            final int size;
            final double sin60 = sqrt(3) / 2;
            final private int pixelSize = 100;

            public TrianglesPanel(int size) {
                this.size = size;
            }

            private void createTrianglesImage() {
                if (g2 != null) {
                    return;
                }

                img = getGraphicsConfiguration().createCompatibleImage(getWidth(), getHeight());
                g2 = img.createGraphics();
                g2.setColor(Color.WHITE);
                g2.fillRect(0, 0, getWidth(), getHeight());
                g2.setColor(Color.BLUE);

                translateAndDrawTriangle(100, 700, -1, size);
            }

            private void translateAndDrawTriangle(int cx, int cy, int ysign, int size) {
                g2.translate(cx, cy);
                if (ysign == -1) {
                    g2.rotate(-PI);
                    g2.translate(-size * pixelSize, 0);
                }

                drawTriangle(size);


                if (ysign == -1) {
                    g2.translate(size * pixelSize, 0);
                    g2.rotate(PI);
                }
                g2.translate(-cx, -cy);
            }

            private void drawTriangle(int size) {
                int x1 = size * pixelSize;
                int y1 = 0;

                int x2 = x1 / 2;
                int y2 = (int) (x1 * sin60);

                int x1c = x1 / 2;
                int y1c = y1 / 2;
                int x2c = x2 / 2;
                int y2c = y2 / 2;
                int x12 = (x1 + x2) / 2;
                int y12 = (y1 + y2) / 2;

                g2.drawLine(0, 0, x1, y1);
                g2.drawLine(0, 0, x2, y2);
                g2.drawLine(x1, y1, x2, y2);

                g2.drawLine(0, 0, x12, y12);
                g2.drawLine(x1, y1, x2c, y2c);
                g2.drawLine(x2, y2, x1c, y1c);

                if (size > 1) {
                    translateAndDrawTriangle(0, 0, 1, size / 2);
                    translateAndDrawTriangle(x1c, y1c, 1, size / 2);
                    translateAndDrawTriangle(x2c, y2c, 1, size / 2);
                    translateAndDrawTriangle(x2c, y2c, -1, size / 2);
                }
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                createTrianglesImage();

                g.drawImage(img, 0, 0, null);
            }
        }
    }
}
