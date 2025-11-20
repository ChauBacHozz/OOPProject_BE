package main;

import com.google.gson.Gson;
import main.api_connect.WeatherAPI;
import main.api_connect.ApiException;
import main.api_module.CurrentWeatherGSON;
import main.api_module.DailyForecastWeatherGSON;
import main.api_module.HourlyForecastWeatherGSON;
import main.db_base.CurrentRowData;
import main.db_connect.DatabaseConnector;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;



public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());
    public static void main(String[] args) {

        DatabaseConnector dbConnector = new DatabaseConnector();
        WeatherAPI wapi = new WeatherAPI();
        Map<String, CurrentWeatherGSON> currentWeatherData = wapi.getWeatherData(CurrentWeatherGSON.class);

        Map<String, Integer> city_id_map = new HashMap<>();
        city_id_map.put("Hanoi", 1);
        city_id_map.put("Danang", 2);
        city_id_map.put("HCM", 3);

        for (Map.Entry<String, CurrentWeatherGSON> entry : currentWeatherData.entrySet()) {
            System.out.println(entry.getKey());
            CurrentWeatherGSON city_curent_data = entry.getValue();
            CurrentRowData city_row = city_curent_data.exportAsRow();
            city_row.setCity_id(city_id_map.get(entry.getKey()));
            dbConnector.insertToCurrentDB(city_row);
        }

    }

}
