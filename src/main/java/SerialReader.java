import com.fazecast.jSerialComm.SerialPort;
import javafx.application.Platform;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * This class handles serial communication with the Arduino and fetches real-time indoor sensor data.
 * It also connects to the OpenWeatherAPI and updates the GUI with both indoor and outdoor data.
 *
 * This is a must in the project as it is the main data source for the program, combining both hardware and online inputs.
 */
public class SerialReader {

    /**
     * This method connects to the Arduino via serial port and starts reading sensor data.
     * It also makes an API call to get outdoor weather data before starting the live updates.
     *
     * This method updates both the Overview and Chart panels in real time using JavaFX threads.
     *
     * @param overviewPanel the panel that shows sensor data values and comfort messages
     * @param chartPanel the panel that draws real-time graphs
     */
    public static void loadOutdoorAndConnectSerial(OverviewPanel overviewPanel, ChartPanel chartPanel) {
        final double[] outdoorTempHolder = new double[1];

        try {
            // This block fetches outdoor weather data once before connecting to the Arduino
            OpenWeatherAPI api = new OpenWeatherAPI();
            double temp = api.getTemperature();
            double humidity = api.getHumidity();
            double pressure = api.getPressure();
            String city = "Toronto";
            outdoorTempHolder[0] = temp;

            // Update the GUI with the outdoor data
            Platform.runLater(() -> overviewPanel.updateOutdoorData(city, temp, humidity, pressure));

        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            // Attempt to open the serial port
            SerialPort comPort = SerialPort.getCommPort("/dev/tty.usbserial-0001");
            comPort.setBaudRate(9600);
            comPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 0, 0);

            if (!comPort.openPort()) {
                System.out.println(" Failed to open serial port.");
                return;
            }

            Thread.sleep(3000);
            System.out.println("Port open. Reading values now...");

            // Begin reading serial input line by line
            BufferedReader reader = new BufferedReader(new InputStreamReader(comPort.getInputStream()));
            String line;

            while ((line = reader.readLine()) != null) {
                line = line.trim();

                // Skip lines that don't look like numeric data
                if (!line.matches("^[0-9.+-].*")) continue;

                String[] parts = line.split(",");
                if (parts.length == 3) {
                    try {
                        // Convert the serial input to doubles
                        double indoorTemp = Double.parseDouble(parts[0]);
                        double indoorHumidity = Double.parseDouble(parts[1]);
                        double indoorPressure = Double.parseDouble(parts[2]);

                        // Use JavaFX thread to update UI components
                        Platform.runLater(() -> {
                            overviewPanel.updateIndoorData(indoorTemp, indoorHumidity, indoorPressure);
                            chartPanel.updateCharts(indoorTemp, indoorHumidity, indoorPressure);

                            String msg = ComfortMessages.evaluate(indoorTemp, outdoorTempHolder[0], indoorPressure);
                            overviewPanel.setComfortMessage(msg);
                        });

                    } catch (NumberFormatException e) {
                        System.out.println(" Parse error: " + e.getMessage());
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

/**
 * End of SerialReader class.
 * This class is responsible for reading and parsing Arduino data, and updating the GUI accordingly.
 */
