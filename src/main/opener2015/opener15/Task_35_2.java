package opener15;

import org.knowm.xchart.Chart;
import org.knowm.xchart.Series;
import org.knowm.xchart.SeriesMarker;
import org.knowm.xchart.SwingWrapper;
import tasks.AbstractTask;
import tasks.Tester;
import utils.log.Logger;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.List;

//Answer :
public class Task_35_2 extends AbstractTask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_35_2());
        Logger.close();
    }

    int n = 1017;

    double tx[] = new double[n];
    double ty[] = new double[n];
    double dx[] = new double[n];
    double dy[] = new double[n];
    int kinds[] = new int[n];
    int SMALL = 0;
    int BIG = 1;

    int BIGS = 1;
    int SMALLS = 2;
    int DIFFS = 3;
    int SAME = 4;

    List<Double> x = new ArrayList<>();
    List<Double> y = new ArrayList<>();

    double tan18 = Math.tan(Math.PI / 10);
    double tan36 = Math.tan(Math.PI / 5);

    double cos18 = Math.cos(Math.PI / 10);
    double cos36 = Math.cos(Math.PI / 5);

    double linearScale = 10;
    double scaleBig = 0.045;
    double scaleSmall = scaleBig * cos18 / cos36;

    Set<Integer> allAngles = new TreeSet<>();
    Set<Integer> allDist = new TreeSet<>();

    public void solving() throws IOException {
        BufferedReader bfr = new BufferedReader(new FileReader("src/main/opener2015/opener15/Task_35.txt"));

        Arrays.fill(kinds, -1);

        for (int i = 0; i < n; ++i) {
            tx[i] = Double.parseDouble(bfr.readLine())*linearScale;
            ty[i] = Double.parseDouble(bfr.readLine())*linearScale;
            dx[i] = Double.parseDouble(bfr.readLine())*linearScale;
            dy[i] = Double.parseDouble(bfr.readLine())*linearScale;
        }

        System.out.println(allDist);

        for (int i = 0; i < n; ++i) {

            int j = (i+n-1)%n;
            int d = (int) ((tx[i] * tx[i] + ty[i] * ty[i])*100000);

            int angle = angle(dx[i], dy[i], dx[j], dy[j]);

            if (d == 10729) {
                kinds[i] = kinds[j] = SMALL;
            } else if (d == 20326) {
                if (kinds[j] == BIG) {
                    kinds[i] = SMALL;
                } else if (kinds[j] == SMALL) {
                    kinds[i] = BIG;
                }
            } else if (d == 28090) {
                kinds[i] = BIG;
                if (angle == 72) {
                    kinds[i] = BIG;
                } else if (angle == 18) {
                    if (kinds[j] == BIG) {
                        kinds[i] = SMALL;
                    } else if (kinds[j] == SMALL) {
                        kinds[i] = BIG;
                    }
                } else if (angle == 54) {
                    if (kinds[j] == BIG) {
                        kinds[i] = SMALL;
                    } else if (kinds[j] == SMALL) {
                        kinds[i] = BIG;
                    }
                }
            }
        }

        for (int i = 0; i < n; ++i) {
            if (kinds[i] == -1) {
                System.out.println("Unknown kind: " + i);
            }
        }

        double currx = 0;
        double curry = 0;
        add(currx, curry);
        for (int i = 0; i < n; ++i) {
            currx += tx[i];
            curry += ty[i];

            double pdx = -dy[i];
            double pdy = dx[i];
            double kind = kinds[i];

            if (kind != -1) {
                double scale1;
                double scale2;
                if (kind == SMALL) {
                    scale1 = scaleSmall;
                    scale2 = scaleSmall * tan18;
                } else {
                    scale1 = scaleBig;
                    scale2 = scaleBig * tan36;
                }

                add(currx, curry);
                add(currx + dx[i] * scale1, curry + dy[i] * scale1);
                add(currx + pdx * scale2, curry + pdy * scale2);
                add(currx, curry);
                add(currx + pdx * scale2, curry + pdy * scale2);
                add(currx - dx[i] * scale1, curry - dy[i] * scale1);
                add(currx, curry);
                add(currx - dx[i] * scale1, curry - dy[i] * scale1);
                add(currx - pdx * scale2, curry - pdy * scale2);
                add(currx, curry);
                add(currx - pdx * scale2, curry - pdy * scale2);
                add(currx + dx[i] * scale1, curry + dy[i] * scale1);
                add(currx, curry);
            }
        }

        createUI();
    }

    private void createUI() {
        // Create Chart
        Chart chart = new Chart(1200, 800);
        chart.getStyleManager().setMarkerSize(3);
        chart.getStyleManager().setPlotGridLinesVisible(false);

        Series series = chart.addSeries("path", toa(x), toa(y));
        series.setMarker(SeriesMarker.CIRCLE);
        series.setLineStyle(new BasicStroke());

        new SwingWrapper(chart).displayChart();
    }

    public int angle(double x1, double y1, double x2, double y2) {
        double cosa = (x1*x2 + y1*y2)/linearScale/linearScale;
        int angle = (int) (Math.acos(cosa)*180/Math.PI);
        return angleKind(angle);
    }

    private int angleKind(int angle) {
        if (angle < 0) {
            angle = -angle;
        }
        if (angle > 90) {
            angle = 180 - angle;
        }
        allAngles.add(angle);

        int angles[] = new int[] {
                18,
                36,
                54,
                72};
        int index = Arrays.binarySearch(angles, angle);
        if (index >= 0) {
            return angles[index];
        }

        index = -index-1;
        if (index == 0) {
            return angles[index];
        }

        if (index == angles.length) {
            return angles[index - 1];
        }

        int error1 = Math.abs(angle - angles[index - 1]);
        int error2 = Math.abs(angle - angles[index]);

        return error1 < error2 ? angles[index - 1] : angles[index];
    }

    public void add(double xd, double yd) {
        x.add(xd);
        y.add(yd);
    }

    public double[] toa(List<Double> lst) {
        double res[] = new double[lst.size()];
        for (int i = 0; i < lst.size(); i++) {
            res[i] = lst.get(i);
        }
        return res;
    }
}
