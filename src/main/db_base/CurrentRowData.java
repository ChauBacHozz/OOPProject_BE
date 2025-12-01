package main.db_base;

import java.time.LocalDateTime;

public class CurrentRowData {
    public int city_id;
    public float temperature;
    public float feellike;
    public float pressure;
    public float humidity;
    public float windspeed;
    public float windeg;
    public float windgust;
    public double rainamount;
    public int cloud;
    public LocalDateTime currentdt;
    public float temp_min;
    public float temp_max;
    public String description;
    public long sunrise;
    public long sunset;
    public String icon;

    public void setCity_id(int city_id) {
        this.city_id = city_id;
    }

    public CurrentRowData(float temperature, float feellike, float pressure, float humidity, float windspeed, float windeg, float windgust, double rainamount, int cloud, LocalDateTime currentdt, float temp_min, float temp_max, String description, long sunrise, long sunset, String icon) {

        this.temperature = temperature;
        this.feellike = feellike;
        this.pressure = pressure;
        this.humidity = humidity;
        this.windspeed = windspeed;
        this.windeg = windeg;
        this.windgust = windgust;
        this.rainamount = rainamount;
        this.cloud = cloud;
        this.currentdt = currentdt;
        this.temp_min = temp_min;
        this.temp_max = temp_max;
        this.description = description;
        this.sunrise = sunrise;
        this.sunset = sunset;
        this.icon = icon;
    }



}
