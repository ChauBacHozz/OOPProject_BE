package main.api_connect;

public class DailyForecastWeather extends BaseWeather {
    public long sunrise;
    public long sunset;
    public long moonrise;
    public long moonset;
    public Float moon_phase;
    public String summary;
    
    public Temp temp;
    public FeelsLike feels_like;
    
    public Float pop;
    public Float rain;
    public Float snow;

    public DailyForecastWeather() {
        super();
    }
}
