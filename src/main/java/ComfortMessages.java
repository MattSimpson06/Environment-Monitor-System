/**
 * This class contains the logic used to evaluate the comfort level of the indoor environment.
 * It compares indoor temperature and pressure against outdoor values to determine what message should be shown.
 *
 * This is important because it allows the system to make basic decisions and give helpful suggestions to the user.
 */
public class ComfortMessages {

    /**
     * This method evaluates the indoor environment and returns a comfort message.
     * It uses the difference between indoor and outdoor temperature, and also checks pressure.
     *
     * This allows the program to respond with a message that helps users decide how to adjust their environment.
     *
     * @param indoorTemp the current indoor temperature
     * @param outdoorTemp the current outdoor temperature from the weather API
     * @param indoorPressure the current indoor pressure from the sensor
     * @return a comfort message string based on the comparison logic
     */
    public static String evaluate(double indoorTemp, double outdoorTemp, double indoorPressure) {
        double diff = indoorTemp - outdoorTemp;

        if (diff >= 3) {
            return "Open a window, it's hot in here!";
        } else if (diff <= -3) {
            return "It's colder inside than outside — turn up the heat!";
        } else if (indoorPressure < 985) {
            return "Low pressure, it might rain soon!";
        } else {
            return "Indoor temp is comfortable.";
        }
    }
}

/**
 * End of ComfortMessages class.
 * This is where the reasoning logic for comfort evaluation is handled.
 */
