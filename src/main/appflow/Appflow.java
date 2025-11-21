package main.appflow;

import main.api_connect.WeatherAPI;
import main.api_module.HourlyForecastWeatherGSON;
import main.db_base.ForecastDailyRowData;
import main.db_connect.DatabaseConnector;

import java.util.HashMap;
import java.util.Map;

public class Appflow {
    private DatabaseConnector dbConnector;
    private WeatherAPI wapi;
    private Map<String, Integer> city_id_map;

    public Appflow () {
        dbConnector = new DatabaseConnector();
        wapi = new WeatherAPI();

        city_id_map = new HashMap<>();
        city_id_map.put("Hanoi", 1);
        city_id_map.put("Danang", 2);
        city_id_map.put("HCM", 3);

    }

    public void insertAPIdata() {

        Map<String, HourlyForecastWeatherGSON> currentWeatherData = wapi.getWeatherData(HourlyForecastWeatherGSON.class);

        for (Map.Entry<String, HourlyForecastWeatherGSON> entry : currentWeatherData.entrySet()) {
            HourlyForecastWeatherGSON city_curent_data = entry.getValue();
            ForecastDailyRowData city_row = city_curent_data.exportAsRow();
            city_row.setCity_id(city_id_map.get(entry.getKey()));
//            dbConnector.insertToCurrentDB(city_row);
        }





    }
}
