package main.db_base;

import java.time.LocalDateTime;


public class ForecastHourlyRowData {
    public int city_id;
    public float temperature;
    public float feellike;
    public float pressure;
    public float humidity;
    public int visibility;
    public float windspeed;
    public float windeg;
    public float windgust;
    public double rainamount;
    public int cloud;
    public LocalDateTime currentdt;
    public int hours;
    public float pop;
    public String icon;

    public void setCity_id(int city_id) {
        this.city_id = city_id;
    }

    public ForecastHourlyRowData(float temperature, float feellike, float pressure, float humidity, int visibility, float windspeed, float windeg, float windgust, double rainamount, int cloud, LocalDateTime currentdt, int hours, float pop, String icon) {
        this.temperature = temperature;
        this.feellike = feellike;
        this.pressure = pressure;
        this.humidity = humidity;
        this.visibility = visibility;
        this.windspeed = windspeed;
        this.windeg = windeg;
        this.windgust = windgust;
        this.rainamount = rainamount;
        this.cloud = cloud;
        this.currentdt = currentdt;
        this.hours = hours;
        this.pop = pop;
        this.icon = icon;
    }


}
