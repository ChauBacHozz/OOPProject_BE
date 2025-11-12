package main.api_connect;

import java.util.List;

public class BaseWeather {
    public long dt;
    public Integer pressure;
    public Integer humidity;
    public Float dew_point;
    public Float uvi;
    public Float clouds;
    public Long visibility;
    public Float wind_speed;
    public Integer wind_deg;
    public Float wind_gust;
    public List<WeatherDescriptorBase> weather;

    public BaseWeather() {
    }
}
