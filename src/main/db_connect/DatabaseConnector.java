package main.db_connect;

import main.db_base.CurrentRowData;
import main.db_base.ForecastDailyRowData;

import java.sql.*;

public class DatabaseConnector {
    String url = "jdbc:mysql://localhost:3306/oop";
    String user = "myuser";
    String password = "mypassword";

    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;

    public DatabaseConnector() {
        try {
            conn = DriverManager.getConnection(url, user, password);

            stmt = conn.createStatement();
//            rs = stmt.
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        } finally {
//            // Đóng kết nối
//            try { if (rs != null) rs.close(); } catch (SQLException e) { }
//            try { if (stmt != null) stmt.close(); } catch (SQLException e) { }
//            try { if (conn != null) conn.close(); } catch (SQLException e) { }
//        }
    }

    public void insertToCurrentDB(CurrentRowData rowData) {
        try {
            String insert_sql = "INSERT INTO currentdata " +
                    "(city_id,temperature,feellike,pressure,humidity,windspeed,windeg,windgust,rainamount,cloud,currentdt) " +
                    "VALUES (?,?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement preparedStatement = this.conn.prepareStatement(insert_sql);

//            Set up for inserting
            preparedStatement.setInt(1, rowData.city_id);
            preparedStatement.setFloat(2, rowData.temperature);
            preparedStatement.setFloat(3, rowData.feellike);
            preparedStatement.setFloat(4, rowData.pressure);
            preparedStatement.setFloat(5, rowData.humidity);
            preparedStatement.setFloat(6, rowData.windspeed);
            preparedStatement.setFloat(7, rowData.windeg);
            preparedStatement.setFloat(8, rowData.windgust);
            preparedStatement.setDouble(9, rowData.rainamount);
            preparedStatement.setFloat(10, rowData.cloud);
            preparedStatement.setTimestamp(11, Timestamp.valueOf(rowData.currentdt));

            preparedStatement.execute();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void insertToForecastingDailyDB(ForecastDailyRowData rowData, String city) {
        try {
            String insert_sql = String.format("""
                    INSERT INTO forecastdaily%s 
                    (days,city_id,temperature,pressure,humidity,visibility,windspeed,windeg,windgust,rainamount,cloud,currentdt,pop,tempmin,tempmax) 
                    VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)""", city.toLowerCase());

            PreparedStatement preparedStatement = this.conn.prepareStatement(insert_sql);

            preparedStatement.setInt(1,rowData.days);
            preparedStatement.setInt(2, rowData.city_id);
            preparedStatement.setFloat(3, rowData.temperature);
            preparedStatement.setFloat(4, rowData.pressure);
            preparedStatement.setFloat(5, rowData.humidity);
            preparedStatement.setFloat(6, rowData.windspeed);
            preparedStatement.setFloat(7, rowData.windeg);
            preparedStatement.setFloat(8, rowData.windgust);
            preparedStatement.setFloat(9, rowData.rainamount);
            preparedStatement.setTimestamp(10, Timestamp.valueOf(rowData.currentdt));
            preparedStatement.setInt(2, rowData.city_id);
            preparedStatement.setInt(2, rowData.city_id);
            preparedStatement.setInt(2, rowData.city_id);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
