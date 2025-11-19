package main.api_module;


import main.api_connect.BaseWeather;
import main.api_module.base.*;

import java.util.List;

class forecast_gson_Main extends BaseGsonMain {
    public float temp_kf;
}
class forecast_gson_Sys extends BaseGsonSys {
    public String pod;
}
class forecast_gson_hourly_element {
    public long dt;
    public forecast_gson_Main main;
    public List<BaseGsonWeather> weather;
    public BaseGsonClouds clouds;
    public BaseGsonWind wind;
    public long visibility;
    public int pop;
    public forecast_gson_Sys sys;
    public String dt_txt;
}
public class ForecastWeatherData {
    public int cod;
    public int message;
    public int cnt;
    public forecast_gson_hourly_element list;



    public ForecastWeatherData() {

    }
}
