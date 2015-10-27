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
import java.util.ArrayList;
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

    List<Double> x = new ArrayList<>();
    List<Double> y = new ArrayList<>();

    double tan72 = Math.tan(2 * Math.PI / 5);
    double sin72 = Math.sin(2 * Math.PI / 5);
    double scaleBig = 0.045;
    double scaleSmall = scaleBig / tan72;
    double scaleSide = scaleBig / sin72;

    public void solving() throws IOException {
        BufferedReader bfr = new BufferedReader(new FileReader("src/main/opener2015/opener15/Task_35.txt"));

        double currx = 0;
        double curry = 0;
        add(currx, curry);
        for (int i = 0; i < n; ++i) {
            tx[i] = Double.parseDouble(bfr.readLine())*10;
            ty[i] = Double.parseDouble(bfr.readLine())*10;
            dx[i] = Double.parseDouble(bfr.readLine())*10;
            dy[i] = Double.parseDouble(bfr.readLine())*10;

            currx += tx[i];
            curry += ty[i];

            add(currx, curry);

            double pdx = -dy[i];
            double pdy = dx[i];

//            add(currx + dx[i] * scaleBig, curry + dy[i] * scaleBig);
//            add(currx - dx[i] * scaleBig, curry - dy[i] * scaleBig);
//            add(currx, curry);

            add(currx + dx[i] * scaleBig, curry + dy[i] * scaleBig);
            add(currx + pdx * scaleSmall, curry + pdy * scaleSmall);
            add(currx - dx[i] * scaleBig, curry - dy[i] * scaleBig);
            add(currx - pdx * scaleSmall, curry - pdy * scaleSmall);
            add(currx + dx[i] * scaleBig, curry + dy[i] * scaleBig);
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
