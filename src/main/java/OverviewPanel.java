import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * This class creates the "Overview" tab of the GUI, where indoor and outdoor values are displayed.
 * It shows temperature, humidity, pressure, and a message based on comfort evaluation.
 *
 * This is good because it gives the user a quick summary of their environment in one place.
 */
public class OverviewPanel {

    // Labels that hold the current values
    private final Label cityValue = new Label("...");
    private final Label outTempValue = new Label("...");
    private final Label outHumidityValue = new Label("...");
    private final Label outPressureValue = new Label("...");
    private final Label inTempValue = new Label("...");
    private final Label inHumidityValue = new Label("...");
    private final Label inPressureValue = new Label("...");
    private final Label comfortMessage = new Label("...");

    private final VBox root = new VBox(20);

    /**
     * This constructor sets up the layout and style for the Overview panel.
     * It builds a grid to organize sensor values and sets up a message area.
     *
     * This allows users to quickly compare indoor and outdoor readings and see action suggestions.
     */
    public OverviewPanel() {
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: white;");

        Label title = new Label("Indoor vs Outdoor Environment Data");
        title.setFont(Font.font("Arial", 24));
        title.setStyle("-fx-background-color: lavender; -fx-padding: 10;");
        title.setMaxWidth(Double.MAX_VALUE);
        title.setAlignment(Pos.CENTER);

        GridPane grid = new GridPane();
        grid.setHgap(30);
        grid.setVgap(15);
        grid.setPadding(new Insets(10));
        grid.setAlignment(Pos.CENTER);

        // Set column widths
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(30);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(35);
        ColumnConstraints col3 = new ColumnConstraints();
        col3.setPercentWidth(35);
        grid.getColumnConstraints().addAll(col1, col2, col3);

        // Add sensor data rows
        addRow(grid, 0, "City", cityValue);
        addRow(grid, 1, "Outdoor Temp", outTempValue);
        addRow(grid, 2, "Outdoor Humidity", outHumidityValue);
        addRow(grid, 3, "Outdoor Pressure", outPressureValue);
        addRow(grid, 4, "Indoor Temp", inTempValue);
        addRow(grid, 5, "Indoor Humidity", inHumidityValue);
        addRow(grid, 6, "Indoor Pressure", inPressureValue);

        // Style comfort message area
        comfortMessage.setFont(Font.font("Arial", 16));
        comfortMessage.setTextFill(Color.DARKBLUE);
        comfortMessage.setAlignment(Pos.CENTER);
        comfortMessage.setMaxWidth(Double.MAX_VALUE);

        root.getChildren().addAll(title, grid, comfortMessage);
    }

    /**
     * This helper method adds a row to the grid with a label and a dynamic value.
     *
     * @param grid the grid to add to
     * @param row the row number
     * @param label the text label for the row
     * @param value the dynamic label that shows the actual sensor value
     */
    private void addRow(GridPane grid, int row, String label, Label value) {
        Label key = new Label(label);
        key.setFont(Font.font("Arial", 16));
        key.setTextFill(Color.BLACK);
        value.setFont(Font.font("Arial", 16));
        grid.add(key, 0, row);
        grid.add(value, 1, row);
    }

    /**
     * This method returns the root VBox that contains the entire layout.
     * This allows the main GUI to place this panel inside a tab.
     *
     * @return the full overview panel as a VBox
     */
    public VBox getOverviewPane() {
        return root;
    }

    /**
     * This method updates the outdoor readings in the panel using live weather API data.
     *
     * @param city the name of the city
     * @param temp outdoor temperature in Celsius
     * @param humidity outdoor humidity in percent
     * @param pressure outdoor pressure in hPa
     */
    public void updateOutdoorData(String city, double temp, double humidity, double pressure) {
        cityValue.setText(city);
        outTempValue.setText(String.format("%.1f°C", temp));
        outHumidityValue.setText(String.format("%.1f%%", humidity));
        outPressureValue.setText(String.format("%.1f hPa", pressure));
    }

    /**
     * This method updates the indoor readings from the Arduino sensors.
     *
     * @param temp indoor temperature
     * @param humidity indoor humidity
     * @param pressure indoor pressure
     */
    public void updateIndoorData(double temp, double humidity, double pressure) {
        inTempValue.setText(String.format("%.1f°C", temp));
        inHumidityValue.setText(String.format("%.1f%%", humidity));
        inPressureValue.setText(String.format("%.1f hPa", pressure));
    }

    /**
     * This method sets the comfort message shown at the bottom of the panel.
     * This message comes from the logic class and tells the user how to adjust the environment.
     *
     * @param message the comfort message to display
     */
    public void setComfortMessage(String message) {
        comfortMessage.setText(message);
    }
}

/**
 * End of OverviewPanel class.
 * This panel displays all sensor readings and comfort messages in a readable format.
 */
