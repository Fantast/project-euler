package solved;

import tasks.ITask;
import tasks.Tester;
import utils.log.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Answer : 115384615384614952
public class Task_349 implements ITask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_349());
        Logger.close();
    }

    public void solving() {
//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                Visualizer frame = new Visualizer();
//                frame.setVisible(true);
//            }
//        });

        long need = 1000000000000000000L;

        long cycleLen = 104;
        long cycleCnt = (need - 11000) / cycleLen;

        long initSteps = need - cycleCnt * cycleLen;

        Mover m = new Mover().forward(initSteps);
        long initCount = m.count();

        long cycleInc = m.forward(cycleLen).count() - initCount;

        System.out.println(initCount + cycleInc * cycleCnt);
    }

    public static class Mover {
        private int moveCount = 0;
        int dx = -1;
        int dy = 0;
        int x = 50;
        int y = 50;

        boolean fill[][] = new boolean[20000][20000];

        public void next() {
            ++moveCount;
            boolean f = fill[x][y];

            if (dx > 0) {
                dx = 0;
                dy = f ? -1 : 1;
            } else if (dx < 0) {
                dx = 0;
                dy = f ? 1 : -1;
            } else if (dy > 0) {
                dx = f ? 1 : -1;
                dy = 0;
            } else {
                dx = f ? -1 : 1;
                dy = 0;
            }

            fill[x][y] = !f;
            x += dx;
            y += dy;
        }

        public void prev() {
            --moveCount;

            x -= dx;
            y -= dy;
            boolean f = !fill[x][y];

            if (dx > 0) {
                dx = 0;
                dy = f ? 1 : -1;
            } else if (dx < 0) {
                dx = 0;
                dy = f ? -1 : 1;
            } else if (dy > 0) {
                dx = f ? -1 : 1;
                dy = 0;
            } else {
                dx = f ? 1 : -1;
                dy = 0;
            }

            fill[x][y] = f;
        }

        public Mover forward(long steps) {
            for (int i = 0; i < steps; ++i) {
                next();
            }
            return this;
        }

        public long count() {
            long cnt = 0;
            for (boolean[] aFill : fill) {
                for (boolean block : aFill) {
                    if (block) {
                        ++cnt;
                    }
                }
            }
            return cnt;
        }
    }

    public class Visualizer extends JFrame {
        private int sqsize = 5;

        boolean forward = true;

        private Timer timer;
        private JLabel lbMoves = new JLabel();
        private Task_349.Visualizer.AntPanel antPanel;

        Mover m = new Mover();

        public Visualizer() {
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(1024, 768);

            final JButton btnStartStop = new JButton("Stop");
            btnStartStop.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (timer.isRunning()) {
                        timer.stop();
                        btnStartStop.setText("Start");
                    } else {
                        timer.start();
                        btnStartStop.setText("Stop");
                    }
                }
            });

            final JButton btnNext = new JButton("Next");
            btnNext.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    forward = true;
                    antPanel.goForward();
                }
            });

            final JButton btnPrev = new JButton("Prev");
            btnPrev.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    forward = false;
                    antPanel.goBackward();
                }
            });

            JPanel topPanel = new JPanel();
            topPanel.add(lbMoves);
            topPanel.add(btnStartStop);
            topPanel.add(btnPrev);
            topPanel.add(btnNext);

            Container contentPane = getContentPane();
            contentPane.add(topPanel, BorderLayout.NORTH);
            antPanel = new AntPanel();
            contentPane.add(antPanel, BorderLayout.CENTER);
        }

        private void updateLabel() {
            lbMoves.setText("" + m.moveCount);
        }

        public class AntPanel extends JPanel {
            public AntPanel() {
                timer = new Timer(1, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (forward) {
                            goForward();
                        } else {
                            goBackward();
                        }
                    }
                });
                timer.setRepeats(true);
                timer.start();

                m.forward(10200);
            }

            public void goForward() {
                m.next();
                updateLabel();
                repaint();
            }

            public void goBackward() {
                m.prev();
                updateLabel();
                repaint();
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                for (int x = 0; x * sqsize < getWidth(); x++) {
                    for (int y = 0; y * sqsize < getHeight(); y++) {
                        g.setColor(m.fill[x][y] ? Color.BLACK : Color.WHITE);

                        g.fillRect(x * sqsize, y * sqsize, sqsize, sqsize);
                    }
                }

                g.setColor(Color.BLUE);
                for (int x = 0; x < getWidth(); x += sqsize) {
                    g.drawLine(x, 0, x, getHeight());
                }
                for (int y = 0; y < getHeight(); y += sqsize) {
                    g.drawLine(0, y, getWidth(), y);
                }
            }
        }
    }
}
