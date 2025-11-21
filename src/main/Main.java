package main;

import com.google.gson.Gson;
import main.api_connect.WeatherAPI;
import main.api_connect.ApiException;
import main.api_module.CurrentWeatherGSON;
import main.api_module.DailyForecastWeatherGSON;
import main.api_module.HourlyForecastWeatherGSON;
import main.appflow.Appflow;
import main.db_base.CurrentRowData;
import main.db_connect.DatabaseConnector;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;



public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());
    public static void main(String[] args) {

        Appflow app = new Appflow();
        app.insertAPIdata();

    }

}
