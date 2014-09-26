package solved;

import tasks.ITask;
import tasks.Tester;
import utils.OtherUtils;
import utils.log.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import static java.lang.Math.*;

//Answer : 1856532.8455
public class Task_317 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_317());
        Logger.close();
    }

    public static final double g = 9.81;
    public static final double v = 20;
    public static final double h = 100;

    private double hmax;
    private double k;

    public void solving() {
//        to clearly see the shape is a parabola :)
//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                Visualizer frame = new Visualizer();
//                frame.setVisible(true);
//            }
//        });

//      so it's a parabola, with top in (0, hmax) and points in (+-w100, 100)

//        double vx = v/sqrt(2);
//        double vy = v/sqrt(2);
//        h == 0 == vy*t - g*t^2/2 == vy - gt/2=>
//        double t = 2*vy / g;
//        double w100 = vx*t == 2*vx*vy/g == 2*vx^2/g == 2*v^2/2/g == v^2/g;
        double toph = v * v / (2 * g);
//        y = k *x^2;
//        k = toph / (w100 * w100) == g/2/(v*v);
        k = g/2/(v*v);
        hmax = h + toph;

        // parabola equation:  y = hmax - k*x^2
        // parabola equation:  x = sqrt((hmax - y)/k)
        // integral: sy = (hmax - y) * log k;

        double vol = PI * (sy(hmax) - sy(0));
        System.out.println(OtherUtils.formatDouble(vol, 4));
    }

    private double sy(double y) {
        return y*(2*hmax - y) / (2*k);
    }

    public class Visualizer extends JFrame {
        public Visualizer() {
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(800, 600);

            getContentPane().add(new FireworksPanel());
        }

        public class FireworksPanel extends JPanel implements ActionListener {
            private BufferedImage img;
            private Graphics2D g2;

            final double dt = 0.01;
            private Timer timer;

            public FireworksPanel() {
                timer = new Timer(1, this);
                timer.setRepeats(true);
                timer.start();
                drawNewParticle(PI/2);
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                drawNewParticle((random() - 0.5) * (2 * PI));
                repaint();
            }

            private void drawNewParticle(double a) {
                double t = 0;
                int ox = 0;
                int oy = (int) h;
                double vx = 0;
                double vy = 0;
                t = -dt;
                vx = v * cos(a);
                vy = v * sin(a);

                while (oy >= 0) {
                    t += dt;
                    int x = (int) round(Visualizer.this.getWidth() / 2 + vx * t);
                    int y = (int) round(h + vy * t - g * t * t / 2.0);
                    if (oy > y) {
                        System.out.print("");
                    }

                    if (g2 != null) {
                        g2.setColor(Color.BLACK);
                        g2.drawLine(ox, getHeight() / 2 - oy, x, getHeight() / 2 - y);
                    }
                    ox = x;
                    oy = y;
                }
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (g2 == null) {
                    img = getGraphicsConfiguration().createCompatibleImage(getWidth(), getHeight());
                    g2 = img.createGraphics();
                    g2.setColor(Color.WHITE);
                    g2.fillRect(0, 0, getWidth(), getHeight());
                }

                g.drawImage(img, 0, 0, null);

                g.setColor(Color.YELLOW);

                int ox = 0;
                int oy = (int) hmax;
                while (oy >= 0) {
                    ox += 1;

                    int x = ox + 1;
                    int y = (int) (hmax - k*x*x);

                    g.drawLine(Visualizer.this.getWidth() / 2 + ox, getHeight() / 2 - oy, Visualizer.this.getWidth() / 2 + x, getHeight() / 2 - y);
                    g.drawLine(Visualizer.this.getWidth() / 2 - ox, getHeight() / 2 - oy, Visualizer.this.getWidth() / 2 - x, getHeight() / 2 - y);

                    ox = x;
                    oy = y;
                }

                g.setColor(Color.BLUE);
                g.drawLine(0, getHeight() / 2, getWidth(), getHeight() / 2);
                g.drawLine(0, (int) (getHeight() / 2 - hmax), getWidth(), (int) (getHeight() / 2 - hmax));
            }
        }
    }
}
