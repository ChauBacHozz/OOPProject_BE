package main.db_base;

public class CurrentAQIRowData {
    public int aqi;
    public int city_id;

    public void setCity_id(int city_id) {
        this.city_id = city_id;
    }

    public CurrentAQIRowData(int aqi) {
        this.aqi = aqi;
    }
}
