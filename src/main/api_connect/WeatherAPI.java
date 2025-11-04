package main.api_connect;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherAPI {
    private String apikey = "fad20e53bfebd7a0e3866aa41dd1900c";
    private String geo_apikey = "d985f00d5c6b2d564322408d7ad29c77";
//    private String op = "TA2";
//    private String z = ;
//    private String x = ;
//    private String y = ;
    private String zipcode = "00120";
    private String country = "VN";
    private String HANOI_lat = "21.0294498";
    private String HANOI_lon = "105.8544441";

//    private String url = "http://maps.openweathermap.org/maps/2.0/weather/TA2/{z}/{x}/{y}?date=1552861800&opacity=0.9&fill_bound=true&appid=36b69ee0026fdc012bc3898d9389d0a2";
    public void apiTest() {
        try {
            String urlStr = String.format(
                    "https://api.openweathermap.org/data/3.0/onecall?lat=%s&lon=%s&appid=%s",
                    this.HANOI_lat,
                    this.HANOI_lon,
                    this.geo_apikey
            );
            URL url = new URL(urlStr);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");


            int responseCode = conn.getResponseCode();
            System.out.println(responseCode);

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            in.close();

            String json = response.toString();
            System.out.println(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void GeocodingAPI() {
        try {
            String urlStr = String.format(
                    "http://api.openweathermap.org/geo/1.0/direct?q=Hanoi,VN&limit=1&appid=%s",
//                    this.zipcode,
//                    this.country,
                    this.geo_apikey
            );
            URL url = new URL(urlStr);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");


            int responseCode = conn.getResponseCode();
            System.out.println(responseCode);

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            in.close();

            String json = response.toString();
            System.out.println(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
