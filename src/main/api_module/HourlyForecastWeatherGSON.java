package main.api_module;

import com.google.gson.annotations.SerializedName;
import main.api_module.base.*;
import main.db_base.ForecastHourlyRowData;

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

class Rain {
    @SerializedName("1h")
    private Double oneHour;

    @SerializedName("3h")
    private Double threeHour;

    // getter
    public Double getOneHour() { return oneHour != null ? oneHour : 0.0; }
    public Double getThreeHour() { return threeHour != null ? threeHour : 0.0; }
}

class forecast_gson_hourly_element {
    public long dt;
    public hourly_gson_Main main;
    public List<BaseGsonWeather> weather;
    public BaseGsonClouds clouds;
    public BaseGsonWind wind;
    public int visibility;
    public float pop;
    public Rain rain;
    public hourly_gson_Sys sys;
    public String dt_txt;
}
public class HourlyForecastWeatherGSON {
    public int cod;
    public float message;
    public int cnt;
    public List<forecast_gson_hourly_element> list;
    public GsonCity city;
//    Constructor
    public HourlyForecastWeatherGSON() {

    }

    public List<ForecastHourlyRowData> exportAsRow() {
        ArrayList<ForecastHourlyRowData> rds_lst = new ArrayList<ForecastHourlyRowData>();
        for (int i = 0; i < this.list.size(); i++) {
            ForecastHourlyRowData rd = new ForecastHourlyRowData(
                    this.list.get(i).main.temp,
                    this.list.get(i).main.feels_like,
                    this.list.get(i).main.pressure,
                    this.list.get(i).main.humidity,
                    this.list.get(i).visibility,
                    this.list.get(i).wind.speed,
                    this.list.get(i).wind.deg,
                    this.list.get(i).wind.gust,
                    (this.list.get(i).rain != null ? this.list.get(i).rain.getOneHour() : 0),
                    this.list.get(i).clouds.all,
                    LocalDateTime.ofInstant(
                            Instant.ofEpochSecond(this.list.get(i).dt),
                            ZoneId.systemDefault()
                    ),
                    i,
                    this.list.get(i).pop,
                    this.list.get(i).weather.get(0).icon
            );

            rds_lst.add(rd);
        }
        return rds_lst;
    }
}
