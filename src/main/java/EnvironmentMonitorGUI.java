import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

/**
 * This is the main class that launches the JavaFX application.
 * It sets up the tabs, initializes the GUI components, and starts serial communication.
 *
 * This is important because it ties all other classes together and handles the starting point of the system.
 */
public class EnvironmentMonitorGUI extends Application {
    private OverviewPanel overviewPanel;
    private ChartPanel chartPanel;

    /**
     * This method is called when the JavaFX application starts.
     * It sets up the tab layout, initializes the overview and chart panels, and starts reading the thread of values.
     *
     * This method allows the project to combine all GUI elements and begin the background process for collecting data.
     *
     * @param stage the main window of the JavaFX application
     */
    @Override
    public void start(Stage stage) {
        overviewPanel = new OverviewPanel();
        chartPanel = new ChartPanel();

        TabPane tabPane = new TabPane();

        // Creates tabs for switching between the overview and charts
        Tab overviewTab = new Tab("Overview", overviewPanel.getOverviewPane());
        Tab chartTab = new Tab("Charts", chartPanel.getChartPane());

        // Makes sure tabs can't be closed by the user
        overviewTab.setClosable(false);
        chartTab.setClosable(false);

        tabPane.getTabs().addAll(overviewTab, chartTab);

        Scene scene = new Scene(tabPane, 800, 850);
        stage.setScene(scene);
        stage.setTitle("Combined Environment Monitor");
        stage.show();

        // Starts the serial communication and weather API data fetch in a background thread
        new Thread(() -> SerialReader.loadOutdoorAndConnectSerial(overviewPanel, chartPanel)).start();
    }

    /**
     * This is the main method that launches the JavaFX application.
     * This is important because it starts the GUI and makes everything visible.
     *
     * @param args command-line arguments, not used in this case
     */
    public static void main(String[] args) {
        launch(args);
    }
}

/**
 * End of EnvironmentMonitorGUI class.
 * This is the entry point for launching the graphical interface and connecting all parts of the system.
 */
