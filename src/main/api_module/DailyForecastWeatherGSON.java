package main.api_module;

import main.api_module.base.*;
import main.db_base.ForecastDailyRowData;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

class daily_gson_Main extends BaseGsonMain {
    public float temp_kf;
}
class daily_gson_Sys extends BaseGsonSys {
    public String pod;
}
class daily_FeelsLike {
    public float day;
    public float night;
    public float eve;
    public float morn;
}
class daily_Temp {
    public float day;
    public float min;
    public float max;
    public float night;
    public float eve;
    public float morn;
}
class forecast_gson_daily_element {
    public long dt;
    public long sunrise;
    public long sunset;
    public daily_Temp temp;
    public daily_FeelsLike feels_like;

    public int pressure;
    public int humidity;
    public List<BaseGsonWeather> weather;

//    Wind
    public float speed;
    public float deg;
    public float gust;

    public int clouds;
    public float pop;
    public float rain;

}
public class DailyForecastWeatherGSON {
    public int cod;
    public float message;
    public int cnt;
    public List<forecast_gson_daily_element> list;
    public GsonCity city;
    public DailyForecastWeatherGSON() {

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
                    this.list.get(i).temp.max,
                    this.list.get(i).weather.get(0).icon
            );

            rds_lst.add(rd);
        }
        return rds_lst;
    }

}
