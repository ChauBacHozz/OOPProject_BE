package main.api_module;


import main.api_module.base.*;
import main.db_base.CurrentAQIRowData;
import main.db_base.CurrentRowData;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

class Aqi_Main {
    public int aqi;
}

class Aqi_components {
    public float co;
    public float no;
    public float no2;
    public float o3;
    public float so2;
    public float pm2_5;
    public float pmp10;
    public float nh3;
}

class Aqi_content {
    public Aqi_Main main;
    public Aqi_components components;
}
public class CurrentAQIGSON {
    public gson_Coordinate coord;
    List<Aqi_content> list;

    public CurrentAQIGSON () {

    }

    public CurrentAQIRowData exportAsRow() {
        CurrentAQIRowData rd = new CurrentAQIRowData(
                list.get(0).main.aqi
        );
        return rd;
    }
}
