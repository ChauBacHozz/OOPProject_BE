package main.api_module;

import main.api_module.base.*;
import main.db_base.ForecastDailyRowData;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

class hourly_gson_Main extends BaseGsonMain {
    public float temp_kf;
}
class hourly_gson_Sys extends BaseGsonSys {
    public String pod;
}
class forecast_gson_hourly_element {
    public long dt;
    public hourly_gson_Main main;
    public List<BaseGsonWeather> weather;
    public BaseGsonClouds clouds;
    public BaseGsonWind wind;
    public long visibility;
    public float pop;
    public hourly_gson_Sys sys;
    public String dt_txt;
}
public class HourlyForecastWeatherGSON {
    public int cod;
    public float message;
    public int cnt;
    public List<forecast_gson_hourly_element> list;
    public GsonCity city;
    public HourlyForecastWeatherGSON() {

    }

    public List<ForecastDailyRowData> exportAsRow() {
        ArrayList<ForecastDailyRowData> rds_lst = new ArrayList<ForecastDailyRowData>();

        for (int i = 0; i < this.list.size(); i++) {
            ForecastDailyRowData rd = new ForecastDailyRowData(
                    i,
                    this.list.get(i).temp.day,
                    this.list.get(i).pressure,
                    this.list.get(i).humidity,
                    this.list.get(i).speed,
                    this.list.get(i).deg,
                    this.list.get(i).gust,
                    this.list.get(i).rain,
                    this.list.get(i).clouds,
                    LocalDateTime.ofInstant(
                            Instant.ofEpochSecond(this.list.get(i).dt),
                            ZoneId.systemDefault()
                    ),
                    this.list.get(i).pop,
                    this.list.get(i).temp.min,
                    this.list.get(i).temp.max
            );

            rds_lst.add(rd);
        }
        return rds_lst;
    }

}
