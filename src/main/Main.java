package main;

import com.google.gson.Gson;
import main.api_connect.WeatherAPI;
import main.api_connect.ApiException;
import main.api_module.CurrentWeatherGSON;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        try {
//            Initialize
            WeatherAPI wapi = new WeatherAPI();

//            Lấy thông tin thời tiết ở thời diểm hiện tại
            Map<String, CurrentWeatherGSON> currentWeatherData = wapi.getCurrentWeatherData();

//            Lấy thông tin thời  tiết forecast của từng thành phố

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Unexpected error", e);
            System.exit(1);
        }
    }

}
