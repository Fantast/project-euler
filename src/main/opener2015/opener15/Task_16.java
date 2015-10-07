package opener15;

import org.knowm.xchart.Chart;
import org.knowm.xchart.Series;
import org.knowm.xchart.SeriesMarker;
import org.knowm.xchart.SwingWrapper;
import tasks.AbstractTask;
import tasks.Tester;
import utils.log.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

//Answer: 778823
//solved using concorde
public class Task_16 extends AbstractTask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_16());
        Logger.close();
    }

    int n = 456;
    int x[] = new int[n];
    int y[] = new int[n];
    double d[][] = new double[n][n];

    public void solving() throws IOException {
        BufferedReader bfr = new BufferedReader(new FileReader("src/main/opener2015/opener15/Task_16.points"));
        Scanner sc = new Scanner(new BufferedReader(new FileReader("src/main/opener2015/opener15/Task_16.sol")));

        String line;
        int curr = 0;
        while ((line = bfr.readLine()) != null) {
            String[] cc = line.split(" ");
            x[curr] = Integer.parseInt(cc[0]);
            y[curr] = Integer.parseInt(cc[1]);
            curr++;
        }

        double nx[] = new double[n];
        double ny[] = new double[n];

        sc.nextInt();

        for (int i = 0; i < n; ++i) {
            int ind = sc.nextInt();
            nx[i] = x[ind];
            ny[i] = y[ind];
        }

        double res = 0;
        for (int i = 0; i < n; i++) {
            res += dist(nx[i], ny[i], nx[(i+1)%n], ny[(i+1)%n]);
        }

        System.out.println(res);

        // Create Chart
        Chart chart = new Chart(1200, 800);
        chart.getStyleManager().setMarkerSize(7);
        chart.getStyleManager().setPlotGridLinesVisible(false);

        Series series = chart.addSeries("path", nx, ny);
        series.setMarker(SeriesMarker.CIRCLE);

        new SwingWrapper(chart).displayChart();
    }

    private double dist(double x1, double y1, double x2, double y2) {
        return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }
}
