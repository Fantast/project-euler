package tasks;

import utils.log.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import static java.lang.Math.PI;
import static java.lang.Math.sqrt;

//Answer :
public class Task_163 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_163());
        Logger.close();
    }

    int n = 1;

    public void solving() {
//        Visualizer.run();

        System.out.println(vertexToSide(112));
        System.out.println(vertexToSide2(112));
        System.out.println(vertexToCenter(112));

        long res = 6 * vertexToSide(n) + // right triangles from vertex to side
                   6 * vertexToCenter(n) + // right triangles from vertex to center
                0;

        System.out.println(res);
    }

    private long vertexToCenter(int n) {
        long res = 0;
        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= i; ++j) {
                res += (i - j) / 2 + 1;
            }
        }
        return res;
    }

    private long vertexToSide2(int n) {

        long res = 0;
        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= i; ++j) {
                res += (i - j) / 2 + 1;
            }
        }
        return res;
    }

    private long vertexToSide(int n) {
//        for (int i = 1; i <= n; ++i) {
//            for (int j = 1; j <= i; ++j) {
//                res += (i - j) / 2 + 1;
//            }
//        }
        long res = 0;
        for (int i = 1; i <= n; ++i) {
//            if (i % 2 != 0) {
//                res += (i*i - 1) / 8 + (i-1) * (i - 3) / 8 + i ;
//                res += (i+1)*(i+1) / 4;
//            } else {
//                res += i/2 * ((i-2)/2) / 2    +   i/2 * ((i-2)/2)/2 + i;
//                res += i*(i+2)/4;
//                res += (i+1)*(i+1) / 4;
//            }
            res += (i + 1) * (i + 1) / 4;
        }

        return res;
    }

    public static class Visualizer extends JFrame {
        public static void run() {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    Visualizer frame = new Visualizer();
                    frame.setVisible(true);
                }
            });
        }

        public Visualizer() {
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setSize(1224, 1000);

            getContentPane().add(new TrianglesPanel());
        }

        public class TrianglesPanel extends JPanel {
            private BufferedImage img;
            private Graphics2D g2;

            int size = 8;
            double sin60 = sqrt(3) / 2;
            private int pixelSize = 100;

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
