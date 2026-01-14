import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * This class contains all unit tests for the temperature monitoring system.
 * It tests the comfort message logic, serial parsing, and weather API values.
 *
 * This is important because it helps verify that the program's logic behaves as expected.
 */
public class TempMonitorTests {

    // --- Comfort message logic tests ---

    /**
     * This test checks if the comfort message is correct when it is hotter indoors than outdoors.
     */
    @Test
    public void testHotIndoorsMessage() {
        String actual = ComfortMessages.evaluate(27.0, 23.0, 990.0);
        assertEquals("Open a window, it's hot in here!", actual);
    }

    /**
     * This test checks if the comfort message is correct when it is colder indoors than outdoors.
     */
    @Test
    public void testColdIndoorsMessage() {
        String actual = ComfortMessages.evaluate(18.0, 22.0, 990.0);
        assertEquals("It's colder inside than outside — turn up the heat!", actual);
    }

    /**
     * This test checks if the comfort message detects low pressure correctly.
     */
    @Test
    public void testLowPressureMessage() {
        String actual = ComfortMessages.evaluate(22.0, 22.0, 980.0);
        assertEquals("Low pressure, it might rain soon!", actual);
    }

    /**
     * This test checks if the system reports that the temperature is comfortable when all conditions are normal.
     */
    @Test
    public void testComfortableMessage() {
        String actual = ComfortMessages.evaluate(22.5, 22.0, 995.0);
        assertEquals("Indoor temp is comfortable.", actual);
    }

    // --- Serial parsing logic test ---

    /**
     * This test simulates parsing a line of serial data from the Arduino and checks that values are extracted correctly.
     */
    @Test
    public void testParseValidSerialLine() {
        String line = "23.5,45.0,995.2";
        String[] parts = line.split(",");

        assertEquals(3, parts.length);

        double temp = Double.parseDouble(parts[0]);
        double humidity = Double.parseDouble(parts[1]);
        double pressure = Double.parseDouble(parts[2]);

        assertEquals(23.5, temp);
        assertEquals(45.0, humidity);
        assertEquals(995.2, pressure);
    }

    // --- Weather API sanity test ---

    /**
     * This test checks that the temperature returned from the OpenWeatherAPI is within a realistic range.
     *
     * This helps ensure that the API connection and conversion to Celsius are working correctly.
     */
    @Test
    public void testWeatherAPIReturnsValidTemperature() throws Exception {
        OpenWeatherAPI api = new OpenWeatherAPI();
        double temp = api.getTemperature();

        assertTrue(temp > -50 && temp < 50, "Temperature is out of expected range");
    }
}

/**
 * End of TempMonitorTests class.
 * These tests ensure key logic in the project is working correctly, and help catch bugs before runtime.
 */
