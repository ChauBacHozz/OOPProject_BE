package main.db_base;


import java.time.LocalDateTime;

public class ForecastDailyRowData {
    public int days;
    public int city_id;
    public float temperature;
    public float pressure;
    public float humidity;
    public float windspeed;
    public float windeg;
    public float windgust;
    public float rainamount;
    public int cloud;
    public LocalDateTime currentdt;
    public float pop;
    public float tempmin;
    public float tempmax;
    public String icon;

    public void setCity_id(int city_id) {
        this.city_id = city_id;
    }

    public ForecastDailyRowData(int days, float temperature, float pressure, float humidity, float windspeed, float windeg, float windgust, float rainamount, int cloud, LocalDateTime currentdt, float pop, float tempmin, float tempmax, String icon) {
        this.days = days;
        this.temperature = temperature;
        this.pressure = pressure;
        this.humidity = humidity;
        this.windspeed = windspeed;
        this.windeg = windeg;
        this.windgust = windgust;
        this.rainamount = rainamount;
        this.cloud = cloud;
        this.currentdt = currentdt;
        this.pop = pop;
        this.tempmin = tempmin;
        this.tempmax = tempmax;
        this.icon = icon;
    }
}
