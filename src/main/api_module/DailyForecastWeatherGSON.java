package main.api_module;

import main.api_module.base.*;

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
}
