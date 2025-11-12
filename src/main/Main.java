package main;

import main.api_connect.WeatherAPI;


public class Main {
    public static void main(String[] args) {
        WeatherAPI wapi = new WeatherAPI();
        wapi.apiTest();
    }
}
