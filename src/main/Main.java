package main;

import main.api_connect.WeatherAPI;
import main.api_connect.OneCallResponse;
import main.api_connect.ApiException;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        try {
            WeatherAPI wapi = new WeatherAPI();

            logger.log(Level.INFO, "Fetching Hanoi weather (sync)...");
            try {
                OneCallResponse hanoiData = wapi.fetchHanoiWeatherSync();
                printWeatherData("Hanoi", hanoiData);
            } catch (ApiException e) {
                if (e.statusCode == 401) {
                    System.err.println("ERROR 401: Invalid or expired API key!");
                    System.err.println("Get a new key from: https://openweathermap.org/api/one-call-api");
                    System.err.println("Then update the API key in WeatherAPI.java or set OPENWEATHER_API_KEY environment variable");
                } else if (e.statusCode == 429) {
                    System.err.println("ERROR 429: Rate limit exceeded. Wait before retrying.");
                } else {
                    System.err.println("API Error: " + e.getMessage());
                }
            }

            logger.log(Level.INFO, "Fetching Da Nang and Ho Chi Minh weather (async)...");
            CompletableFuture<OneCallResponse> daNangFuture = wapi.fetchDaNangWeatherAsync();
            CompletableFuture<OneCallResponse> hcmFuture = wapi.fetchHoChiMinhWeatherAsync();

            CompletableFuture.allOf(daNangFuture, hcmFuture).join();

            OneCallResponse daNangData = daNangFuture.getNow(null);
            OneCallResponse hcmData = hcmFuture.getNow(null);

            if (daNangData != null) {
                printWeatherData("Da Nang", daNangData);
            }
            if (hcmData != null) {
                printWeatherData("Ho Chi Minh", hcmData);
            }

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Unexpected error", e);
            System.exit(1);
        }
    }

    private static void printWeatherData(String cityName, OneCallResponse data) {
        if (data == null) {
            logger.log(Level.WARNING, "No data for " + cityName);
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("\n====== Weather Data for ").append(cityName).append(" ======\n");
        sb.append("Coordinates: ").append(data.lat).append(", ").append(data.lon).append("\n");
        sb.append("Timezone: ").append(data.timezone).append("\n");

        if (data.current != null) {
            sb.append("\n--- Current Weather ---\n");
            sb.append("Temperature: ").append(data.current.temp).append("°C\n");
            sb.append("Feels Like: ").append(data.current.feels_like).append("°C\n");
            sb.append("Humidity: ").append(data.current.humidity).append("%\n");
            sb.append("Pressure: ").append(data.current.pressure).append(" hPa\n");
            sb.append("Wind Speed: ").append(data.current.wind_speed).append(" m/s\n");
            sb.append("Wind Degree: ").append(data.current.wind_deg).append("°\n");
            sb.append("Clouds: ").append(data.current.clouds).append("%\n");
            sb.append("Visibility: ").append(data.current.visibility).append(" m\n");
            sb.append("UV Index: ").append(data.current.uvi).append("\n");

            if (data.current.weather != null && !data.current.weather.isEmpty()) {
                sb.append("Weather: ");
                data.current.weather.forEach(w -> 
                    sb.append(w.main).append(" (").append(w.description).append(") ")
                );
                sb.append("\n");
            }
        }

        if (data.hourly != null && !data.hourly.isEmpty()) {
            sb.append("\n--- Hourly Forecast (next ").append(data.hourly.size()).append(" hours) ---\n");
            data.hourly.stream().limit(3).forEach(h ->
                sb.append("  Temp: ").append(h.temp).append("°C, PoP: ").append(h.pop).append("\n")
            );
        }

        if (data.daily != null && !data.daily.isEmpty()) {
            sb.append("\n--- Daily Forecast (next ").append(data.daily.size()).append(" days) ---\n");
            data.daily.stream().limit(3).forEach(d -> {
                sb.append("  Day: ");
                if (d.temp != null) {
                    sb.append("Max=").append(d.temp.max).append("°C, Min=").append(d.temp.min).append("°C");
                }
                sb.append(", PoP=").append(d.pop).append("\n");
            });
        }

        if (data.alerts != null && !data.alerts.isEmpty()) {
            sb.append("\n--- Alerts (").append(data.alerts.size()).append(") ---\n");
            data.alerts.forEach(a ->
                sb.append("  ").append(a.event).append(" from ").append(a.sender_name).append("\n")
            );
        }

        System.out.println(sb.toString());
    }
}
