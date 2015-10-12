package opener15;

import org.knowm.xchart.*;
import tasks.AbstractTask;
import tasks.Tester;
import utils.log.Logger;

import java.awt.*;

//Answer :
public class Task_25 extends AbstractTask {
    public static void main(String[] args) {
        Logger.init("default.log");
        Tester.test(new Task_25());
        Logger.close();
    }

    int x[] = new int[]{0, 100, 100, 120, 120, 140, 140, 160, 160, 200, 200, 220, 220, 260, 260, 280, 280, 300, 300, 320, 320, 372, 372, 420, 420, 460, 460, 480, 480, 580, 580, 600, 600, 660, 660, 680, 680, 720, 720, 700, 700, 680, 680, 660, 660, 620, 620, 580, 580, 560, 560, 520, 520, 500, 500, 480, 480, 460, 460, 400, 400, 360, 360, 320, 320, 280, 280, 260, 260, 240, 240, 220, 220, 200, 200, 180, 180, 160, 160, 140, 140, 120, 120, 100, 100, 80, 80, 60, 60, 20, 20, 0};
    int y[] = new int[]{260, 260, 240, 240, 20, 20, 0, 0, 20, 20, 60, 60, 20, 20, 160, 160, 260, 260, 160, 160, 60, 60, 120, 120, 40, 40, 80, 80, 60, 60, 80, 80, 40, 40, 140, 140, 200, 200, 260, 260, 300, 300, 500, 500, 480, 480, 440, 440, 420, 420, 360, 360, 300, 300, 360, 360, 480, 480, 460, 460, 380, 380, 360, 360, 420, 420, 460, 460, 480, 480, 440, 440, 280, 280, 260, 260, 340, 340, 380, 380, 420, 420, 400, 400, 360, 360, 340, 340 , 320, 320, 280, 280};

    public void solving() {
        System.out.println(x.length);
        System.out.println(y.length);

        // Create Chart
        Chart chart = new Chart(1200, 800);
        chart.getStyleManager().setChartType(StyleManager.ChartType.Line);
        chart.getStyleManager().setMarkerSize(1);
        chart.getStyleManager().setPlotGridLinesVisible(false);

        for (int i = 0; i < 10; ++i) {
            for (int j = i+1; j < 10; ++j) {
                double lx[] = new double[] {x[i], x[j]};
                double ly[] = new double[] {y[i], y[j]};
                Series line = chart.addSeries("l_" + i + "_" + j, lx, ly);
                line.setLineStyle(SeriesLineStyle.SOLID);
                line.setLineColor(Color.blue);
            }
        }

        Series series = chart.addSeries("path", toDouble(x), toDouble(y));
        series.setMarker(SeriesMarker.CIRCLE);
        series.setLineStyle(SeriesLineStyle.SOLID);
        series.setLineColor(Color.black);

        SwingWrapper swingWrapper = new SwingWrapper(chart);
        swingWrapper.displayChart();
    }

    double[] toDouble(int[] a) {
        double r[] = new double[a.length];
        for (int i = 0; i < a.length; i++) {
            r[i] = a[i];
        }
        return r;
    }

    double[] flip(double[] a) {
        double r[] = new double[a.length];
        for (int i = 0; i < a.length; i++) {
            r[i] = 500 - a[i];
        }
        return r;
    }
}
