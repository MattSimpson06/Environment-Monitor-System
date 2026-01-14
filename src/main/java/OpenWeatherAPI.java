import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * This class connects to the OpenWeatherAPI to get real-time outdoor weather data.
 * It fetches temperature, humidity, and pressure for use in the Java GUI and decision-making logic.
 *
 * It allows the system to compare indoor conditions with outdoor conditions.
 */
public class OpenWeatherAPI {
    private static final String API_KEY = "3c1878a98547077b1db10ad79b722b1a"; // Your actual API key
    private static final String CITY = "Toronto";
    private static final String BASE_URL =
            "https://api.openweathermap.org/data/2.5/weather?q=" + CITY + "&appid=" + API_KEY;

    private final OkHttpClient client = new OkHttpClient();

    /**
     * This method sends a request to the weather API and returns the raw JSON weather data.
     * It is used internally by all other methods in this class.
     *
     * @return the JSON object containing current weather data for the specified city
     * @throws Exception if the request fails or data can't be parsed
     */
    private JsonObject fetchWeatherData() throws Exception {
        Request request = new Request.Builder().url(BASE_URL).build();
        try (Response response = client.newCall(request).execute()) {
            String json = response.body().string();
            return JsonParser.parseString(json).getAsJsonObject();
        }
    }

    /**
     * This method retrieves the current temperature from the OpenWeatherAPI.
     * It converts the temperature from Kelvin to Celsius and rounds it.
     *
     * @return the outdoor temperature in Celsius
     * @throws Exception if there is an issue retrieving or parsing the data
     */
    public double getTemperature() throws Exception {
        JsonObject data = fetchWeatherData();
        double kelvin = data.getAsJsonObject("main").get("temp").getAsDouble();
        return Math.round((kelvin - 273.15) * 100.0) / 100.0; // Convert to °C
    }

    /**
     * This method retrieves the current humidity from the OpenWeatherAPI.
     *
     * @return the outdoor humidity percentage
     * @throws Exception if there is an issue retrieving or parsing the data
     */
    public double getHumidity() throws Exception {
        JsonObject data = fetchWeatherData();
        return data.getAsJsonObject("main").get("humidity").getAsDouble();
    }

    /**
     * This method retrieves the current pressure from the OpenWeatherAPI.
     *
     * @return the outdoor atmospheric pressure in hPa
     * @throws Exception if there is an issue retrieving or parsing the data
     */
    public double getPressure() throws Exception {
        JsonObject data = fetchWeatherData();
        return data.getAsJsonObject("main").get("pressure").getAsDouble();
    }
}

/**
 * End of OpenWeatherAPI class.
 * This class helps the project include real outdoor data and compare it with indoor conditions.
 */
