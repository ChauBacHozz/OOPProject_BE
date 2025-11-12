package main.api_connect;

import java.util.List;

public class OneCallResponse {
    public Double lat;
    public Double lon;
    public String timezone;
    public Integer timezone_offset;

    public CurrentWeather current;
    public List<HourlyForecastWeather> hourly;
    public List<DailyForecastWeather> daily;
    public List<Alert> alerts;

    public OneCallResponse() {
    }
}
