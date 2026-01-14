import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;

/**
 * This class handles the chart panel of the application.
 * It creates and displays live-updating line charts for temperature, humidity, and pressure.
 */
public class ChartPanel {

    // These series hold the data points that are drawn on the charts.
    private final XYChart.Series<Number, Number> tempSeries = new XYChart.Series<>();
    private final XYChart.Series<Number, Number> humiditySeries = new XYChart.Series<>();
    private final XYChart.Series<Number, Number> pressureSeries = new XYChart.Series<>();

    // This index keeps track of how many data points have been added.
    private int timeIndex = 0;

    // This VBox contains all three charts stacked vertically.
    private final VBox chartBox = new VBox(20);

    /**
     * This constructor sets up the chart panel by creating three separate charts:
     * one for temperature, one for humidity, and one for pressure.
     *
     * This method also sets labels, axis limits, and formatting styles for clarity.
     */
    public ChartPanel() {
        chartBox.setPadding(new Insets(15));

        // Temperature chart setup
        NumberAxis tempX = new NumberAxis();
        NumberAxis tempY = new NumberAxis(20.0, 28.0, 0.5);
        tempY.setLabel("Temperature (°C)");
        LineChart<Number, Number> tempChart = new LineChart<>(tempX, tempY);
        tempChart.setTitle("Indoor Temperature");
        tempChart.setAnimated(false);
        tempChart.setCreateSymbols(false);
        tempSeries.setName("Temp (°C)");
        tempChart.getData().add(tempSeries);
        tempChart.setMinHeight(250); // Ensures chart has enough space

        // Humidity chart setup
        NumberAxis humX = new NumberAxis();
        NumberAxis humY = new NumberAxis(30, 90, 5);
        humY.setLabel("Humidity (%)");
        LineChart<Number, Number> humChart = new LineChart<>(humX, humY);
        humChart.setTitle("Indoor Humidity");
        humChart.setAnimated(false);
        humChart.setCreateSymbols(false);
        humiditySeries.setName("Humidity (%)");
        humChart.getData().add(humiditySeries);
        humChart.setMinHeight(250);

        // Pressure chart setup
        NumberAxis presX = new NumberAxis();
        NumberAxis presY = new NumberAxis(960, 1050, 5);
        presY.setLabel("Pressure (hPa)");
        LineChart<Number, Number> presChart = new LineChart<>(presX, presY);
        presChart.setTitle("Indoor Pressure");
        presChart.setAnimated(false);
        presChart.setCreateSymbols(false);
        pressureSeries.setName("Pressure (hPa)");
        presChart.getData().add(pressureSeries);
        presChart.setMinHeight(250);

        chartBox.getChildren().addAll(tempChart, humChart, presChart);
    }

    /**
     * This method allows the GUI to access the VBox that contains all the charts.
     * This is important because the main GUI needs to add this to its chart tab.
     *
     * @return VBox that holds the charts
     */
    public VBox getChartPane() {
        return chartBox;
    }

    /**
     * This method updates all three charts with the latest sensor values.
     * It adds a new data point for each chart and removes the oldest one if there are more than 50.
     *
     * This allows the charts to scroll forward in time and show real-time sensor behavior.
     *
     * @param temperature the latest indoor temperature reading
     * @param humidity the latest indoor humidity reading
     * @param pressure the latest indoor pressure reading
     */
    public void updateCharts(double temperature, double humidity, double pressure) {
        int index = timeIndex++;
        tempSeries.getData().add(new XYChart.Data<>(index, temperature));
        humiditySeries.getData().add(new XYChart.Data<>(index, humidity));
        pressureSeries.getData().add(new XYChart.Data<>(index, pressure));

        // This prevents the charts from getting overloaded with data
        if (tempSeries.getData().size() > 50) tempSeries.getData().remove(0);
        if (humiditySeries.getData().size() > 50) humiditySeries.getData().remove(0);
        if (pressureSeries.getData().size() > 50) pressureSeries.getData().remove(0);
    }
}

/**
 * End of ChartPanel class.
 * This class is responsible for the visual tracking of sensor data over time.
 */
