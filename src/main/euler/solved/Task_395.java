package solved;

import tasks.ITask;
import tasks.Tester;
import utils.OtherUtils;
import utils.log.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.Queue;

import static java.lang.Math.*;

//Answer : 28.2453753155
public class Task_395 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_395());
        Logger.close();
    }

    static double EPS = 1e-15;
    static double sq25 = Math.sqrt(2)*5;

    static class Triangle {
        double x;
        double h;
        double a;
        double sin;
        double cos;

        Triangle(double a, double x, double h, double sin, double cos) {
            this.x = x;
            this.h = h;
            this.a = a;
            this.sin = sin;
            this.cos = cos;
        }

        public Triangle big() {
            double ns = (sin*4.0 + cos*3.0)/5.0;
            double nc = (cos*4.0 - sin*3.0)/5.0;
            double na = 4.0*a/5.0;
            double nh = h + na*nc;
            double nx = x - na*ns;
            return new Triangle(na, nx, nh, ns, nc);
        }
        public Triangle small() {
            double ns = (sin*3.0 - cos*4.0)/5.0;
            double nc = (cos*3.0 + sin*4.0)/5.0;
            double na = 3.0*a/5.0;
            double nh = h + a*sin + na*(nc-ns);
            double nx = x + a*cos + na*(-ns - nc);
            return new Triangle(na, nx, nh, ns, nc);
        }

        public boolean canBeHigher(double hmax) {
            return h + a*sq25 > hmax;
        }
    }

    public void solving() {
//        Visualizer.run();

        double hmax = findMax(0, 1, 0, 1);
        double hmin = findMax(0, -1, 0, -1);
        double lm = findMax(1, 0, -1, 0);
        double rm = findMax(-1, 0, 1, 0);
        double res = (rm + lm)*(hmax + hmin);
        System.out.println();
        System.out.println(res);
        System.out.println(OtherUtils.formatDouble(res, 10));
    }

    private double findMax(double x, double h, double sin, double cos) {
        double hmax = 0;
        Queue<Triangle> q = new LinkedList<Triangle>();
        q.add(new Triangle(1, x, h, sin, cos));
        while (!q.isEmpty()) {
            Triangle t = q.poll();
            if (t.a > EPS && t.canBeHigher(hmax)) {
                q.add(t.big());
                q.add(t.small());
            }
            hmax = Math.max(hmax, t.h);
        }
        System.out.println(hmax);
        return hmax;
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

            private int scale = 100;

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

                g2.translate(500, 700);
                g2.scale(1, -1);

                double hmax = 0;
                Queue<Triangle> q = new LinkedList<Triangle>();
                q.add(new Triangle(1, 0, -1, 0, -1)); //hmin
//                q.add(new Triangle(1, 0, 1, 0, 1)); //hmax
//                q.add(new Triangle(1, 1, 0, -1, 0)); //lmax
//                q.add(new Triangle(1, -1, 0, 1, 0)); //rmax
                while (!q.isEmpty()) {
                    Triangle t = q.poll();
                    if (t.a > EPS && t.canBeHigher(hmax)) {
                        q.add(t.big());
                        q.add(t.small());
                    }
                    hmax = Math.max(hmax, t.h);
                    translateAndDrawTriangle(t);
                }
                System.out.println(hmax);
            }


            private void translateAndDrawTriangle(Triangle t) {
                double alpha = arcsin(t.sin, t.cos);
                double alDeg = toDegrees(alpha);
                System.out.println(alDeg);

                g2.translate(t.x*scale, t.h*scale);
                g2.rotate(alpha);

                int side = (int) (t.a*scale);

//                g2.drawLine(0, 0, side + 10, 0);
//                g2.drawLine(0, 0, 0, -side - 10);

                g2.drawLine(0, 0, side, 0);
                g2.drawLine(0, 0, 0, -side);
                g2.drawLine(0, -side, side, -side);
                g2.drawLine(side, 0, side, -side);

                g2.rotate(-alpha);
                g2.translate(-t.x*scale, -t.h*scale);
            }

            private double arcsin(double sin, double cos) {
                double a = asin(sin);

                if (cos < 0) {
                    a = sin >= 0
                        ? PI - a
                        : -PI - a;
                }
                return a;
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
