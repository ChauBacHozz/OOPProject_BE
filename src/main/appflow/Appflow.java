package main.appflow;

import main.api_connect.WeatherAPI;
import main.api_module.CurrentWeatherGSON;
import main.api_module.DailyForecastWeatherGSON;
import main.api_module.HourlyForecastWeatherGSON;
import main.db_base.CurrentRowData;
import main.db_base.ForecastDailyRowData;
import main.db_base.ForecastHourlyRowData;
import main.db_connect.DatabaseConnector;

import java.util.HashMap;
import java.util.List;
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

    public void execute() {
        insertCurrentAPIData();
        insertForecastHourlyAPIdata();
        insertForecastDailyAPIdata();
    }

    public void insertCurrentAPIData() {
        Map<String, CurrentWeatherGSON> currentWeatherData = wapi.getWeatherData(CurrentWeatherGSON.class);
        for (Map.Entry<String, CurrentWeatherGSON> entry : currentWeatherData.entrySet()) {
            CurrentWeatherGSON city_curent_data = entry.getValue();
            CurrentRowData city_row = city_curent_data.exportAsRow();
            city_row.setCity_id(city_id_map.get(entry.getKey()));
            dbConnector.insertToCurrentDB(city_row);
        }
    }



    public void insertForecastHourlyAPIdata() {
        Map<String, HourlyForecastWeatherGSON> currentWeatherData = wapi.getWeatherData(HourlyForecastWeatherGSON.class);

        dbConnector.truncateTable("hourly");
        for (Map.Entry<String, HourlyForecastWeatherGSON> entry : currentWeatherData.entrySet()) {
            HourlyForecastWeatherGSON city_curent_data = entry.getValue();
            List<ForecastHourlyRowData> city_row = city_curent_data.exportAsRow();

            for (ForecastHourlyRowData rd : city_row) {
                rd.setCity_id(city_id_map.get(entry.getKey()));
                dbConnector.insertToForecastingHourlyDB(rd, entry.getKey());
            }
        }
    }

    public void insertForecastDailyAPIdata() {
        Map<String, DailyForecastWeatherGSON> currentWeatherData = wapi.getWeatherData(DailyForecastWeatherGSON.class);

        dbConnector.truncateTable("daily");
        for (Map.Entry<String, DailyForecastWeatherGSON> entry : currentWeatherData.entrySet()) {
            DailyForecastWeatherGSON city_curent_data = entry.getValue();
            List<ForecastDailyRowData> city_row = city_curent_data.exportAsRow();

            for (ForecastDailyRowData rd : city_row) {
                rd.setCity_id(city_id_map.get(entry.getKey()));
                dbConnector.insertToForecastingDailyDB(rd, entry.getKey());
            }
        }
    }
}
