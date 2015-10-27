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

    public void solving() throws IOException {
        BufferedReader bfr = new BufferedReader(new FileReader("src/main/opener2015/opener15/Task_35.txt"));

        for (int i = 0; i < n; ++i) {
            tx[i] = Double.parseDouble(bfr.readLine())*linearScale;
            ty[i] = Double.parseDouble(bfr.readLine())*linearScale;
            dx[i] = Double.parseDouble(bfr.readLine())*linearScale;
            dy[i] = Double.parseDouble(bfr.readLine())*linearScale;
        }

        Arrays.fill(kinds, -1);

        for (int i = 0; i < n; ++i) {
            int j = (i+1)%n;
            int kind = angle(dx[i], dy[i], dx[j], dy[j]);
            if (kind == BIGS) {
                kinds[i] = kinds[j] = BIG;
            } else if (kind == SMALLS) {
                kinds[i] = kinds[j] = SMALL;
            } else if (kind == DIFFS) {
                //small + big
                if (kinds[i] == BIG) {
                    kinds[j] = SMALL;
                } else if (kinds[i] == SMALL) {
                    kinds[j] = BIG;
                } else if (kinds[j] == BIG) {
                    kinds[i] = SMALL;
                } else if (kinds[j] == SMALL) {
                    kinds[i] = BIG;
                }
            } else if (kind == SAME) {
                if (kinds[i] != -1) {
                    kinds[j] = kinds[i];
                } else if (kinds[j] != -1) {
                    kinds[i] = kinds[j];
                }
            }
        }

        for (int i = 0; i < n; ++i) {
            if (kinds[i] == -1) {
                System.out.println("Unknown kind: " + i);
            }
        }

//        System.out.println(all);
//        System.exit(1);

        double currx = 0;
        double curry = 0;
        add(currx, curry);
        for (int i = 0; i < n; ++i) {
            currx += tx[i];
            curry += ty[i];

            add(currx, curry);

            double pdx = -dy[i];
            double pdy = dx[i];
            double kind = kinds[i];

            double scale1;
            double scale2;
            if (kind == SMALL) {
                scale1 = scaleSmall;
                scale2 = scaleSmall * tan18;
            } else {
                scale1 = scaleBig;
                scale2 = scaleBig * tan36;
            }

            add(currx + dx[i] * scale1, curry + dy[i] * scale1);
            add(currx + pdx * scale2, curry + pdy * scale2);
            add(currx - dx[i] * scale1, curry - dy[i] * scale1);
            add(currx - pdx * scale2, curry - pdy * scale2);
            add(currx + dx[i] * scale1, curry + dy[i] * scale1);
            add(currx, curry);
        }


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
        double cosa = (x1*y1 + x2*y2)/linearScale/linearScale;
        int angle = (int) (Math.acos(cosa)*180/Math.PI);
        return angleKind(angle);
    }

    Set<Integer> all = new TreeSet<>();
    private int angleKind(int angle) {
        if (angle < 0) {
            angle = -angle;
        }
        if (angle > 90) {
            angle = 180 - angle;
        }
        all.add(angle);

//        int kinds[] = new int[]  {5,  1,  5,  1,  5,  2,  5,  5};
        int kinds[] = new int[]  {
                SAME,
                DIFFS,
                SMALLS,
                DIFFS,
                DIFFS,
                BIGS,
                SMALLS,
                BIGS};
        int angles[] = new int[] {
                0,
                18,
                40,
                54,
                62,
                72,
                80,
                90};
        int index = Arrays.binarySearch(angles, angle);
        if (index >= 0) {
            return kinds[index];
        }

        index = -index-1;
        if (index == 0) {
            return kinds[index];
        }

        if (index == angles.length) {
            return kinds[index - 1];
        }

        int error1 = Math.abs(angle - angles[index - 1]);
        int error2 = Math.abs(angle - angles[index]);

        return error1 < error2 ? kinds[index - 1] : kinds[index];
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
