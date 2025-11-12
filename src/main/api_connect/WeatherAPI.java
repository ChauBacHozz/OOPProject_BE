package main.api_connect;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.FieldNamingPolicy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Logger;
import java.util.logging.Level;

public class WeatherAPI {
    private static final Logger logger = Logger.getLogger(WeatherAPI.class.getName());
    
    private String apikey = "fad20e53bfebd7a0e3866aa41dd1900c";
    private String geo_apikey = "d985f00d5c6b2d564322408d7ad29c77";
    private String zipcode = "00120";
    private String country = "VN";
    private String HANOI_lat = "21.0294498";
    private String HANOI_lon = "105.8544441";
    private String DANANG_lat = "16.068";
    private String DANANG_lon = "108.212";
    private String HOCHIMINH_lat = "10.776325";
    private String HOCHIMINH_lon = "106.7012016";

    private final Gson gson;

    public WeatherAPI() {
        this.gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .setPrettyPrinting()
                .create();
        logger.log(Level.INFO, "WeatherAPI initialized");
    }

    public CompletableFuture<OneCallResponse> fetchWeatherAsync(String lat, String lon) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return fetchWeatherSync(lat, lon);
            } catch (ApiException e) {
                logger.log(Level.SEVERE, "Failed to fetch weather data for lat=" + lat + ", lon=" + lon, e);
                throw new RuntimeException(e);
            }
        });
    }

    public OneCallResponse fetchWeatherSync(String lat, String lon) throws ApiException {
        if (lat == null || lon == null || lat.isEmpty() || lon.isEmpty()) {
            throw new ApiException("Latitude and longitude cannot be null or empty");
        }

        String urlStr = String.format(
                "https://api.openweathermap.org/data/3.0/onecall?lat=%s&lon=%s&appid=%s",
                lat, lon, this.apikey
        );

        return executeHttpGet(urlStr, OneCallResponse.class);
    }

    public OneCallResponse fetchHanoiWeatherSync() throws ApiException {
        return fetchWeatherSync(HANOI_lat, HANOI_lon);
    }

    public CompletableFuture<OneCallResponse> fetchHanoiWeatherAsync() {
        return fetchWeatherAsync(HANOI_lat, HANOI_lon);
    }

    public CompletableFuture<OneCallResponse> fetchDaNangWeatherAsync() {
        return fetchWeatherAsync(DANANG_lat, DANANG_lon);
    }

    public CompletableFuture<OneCallResponse> fetchHoChiMinhWeatherAsync() {
        return fetchWeatherAsync(HOCHIMINH_lat, HOCHIMINH_lon);
    }

    private <T> T executeHttpGet(String urlStr, Class<T> responseClass) throws ApiException {
        HttpURLConnection conn = null;
        BufferedReader in = null;
        try {
            URL url = new URL(urlStr);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(10000);
            conn.setReadTimeout(10000);

            int responseCode = conn.getResponseCode();
            logger.log(Level.INFO, "HTTP Response Code: " + responseCode);

            if (responseCode != HttpURLConnection.HTTP_OK) {
                throw new ApiException("HTTP Error " + responseCode + " from API", responseCode);
            }

            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            String json = response.toString();
            logger.log(Level.FINE, "Received JSON response (length=" + json.length() + ")");

            T result = gson.fromJson(json, responseClass);
            if (result == null) {
                throw new ApiException("Failed to parse JSON response");
            }
            return result;

        } catch (ApiException e) {
            throw e;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Exception during HTTP GET: " + e.getMessage(), e);
            throw new ApiException("Network or parsing error: " + e.getMessage(), -1);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (Exception e) {
                    logger.log(Level.WARNING, "Failed to close BufferedReader", e);
                }
            }
            if (conn != null) {
                conn.disconnect();
            }
        }
    }

    public CompletableFuture<String> geocodeAsync(String cityName, String countryCode) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return geocodeSync(cityName, countryCode);
            } catch (ApiException e) {
                logger.log(Level.SEVERE, "Geocoding failed for " + cityName, e);
                throw new RuntimeException(e);
            }
        });
    }

    public String geocodeSync(String cityName, String countryCode) throws ApiException {
        if (cityName == null || cityName.isEmpty()) {
            throw new ApiException("City name cannot be null or empty");
        }

        String query = countryCode != null && !countryCode.isEmpty()
                ? cityName + "," + countryCode
                : cityName;

        String urlStr = String.format(
                "https://api.openweathermap.org/geo/1.0/direct?q=%s&limit=1&appid=%s",
                query, this.geo_apikey
        );

        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(urlStr).openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(10000);
            int responseCode = conn.getResponseCode();
            logger.log(Level.INFO, "Geocoding API HTTP Response: " + responseCode);

            if (responseCode != HttpURLConnection.HTTP_OK) {
                throw new ApiException("Geocoding HTTP Error " + responseCode, responseCode);
            }

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            conn.disconnect();
            return response.toString();
        } catch (ApiException e) {
            throw e;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Geocoding request failed", e);
            throw new ApiException("Geocoding error: " + e.getMessage());
        }
    }
}

