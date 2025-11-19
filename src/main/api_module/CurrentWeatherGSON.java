package main.api_module;


import main.api_module.base.*;
import java.util.List;

class gson_Coordinate extends BaseGsonCoordinate {

}

class gson_Weather extends BaseGsonWeather {

}

class gson_Main extends BaseGsonMain {

}

class gson_Wind extends BaseGsonWind {

}

class gson_Clouds extends BaseGsonClouds {
}

class gson_Sys extends BaseGsonSys {

}

public class CurrentWeatherGSON {
    public gson_Coordinate coord;
    public List<gson_Weather> weather;
    public String base;
    public gson_Main main;
    public gson_Wind wind;
    public gson_Clouds clouds;
    public long dt;
    public gson_Sys sys;
    public int timezone;
    public long id;
    public String name;
    public int cod;

    public CurrentWeatherGSON () {

    }
}
