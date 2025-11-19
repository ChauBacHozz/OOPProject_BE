package main.db_connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

public class DatabaseConnector {
    String url = "jdbc:mysql://localhost:3306/oop";
    String user = "myuser";
    String password = "mypassword";

    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;

    public void connect() {
        try {
            conn = DriverManager.getConnection(url, user, password);

            stmt = conn.createStatement();
//            rs = stmt.
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Đóng kết nối
            try { if (rs != null) rs.close(); } catch (SQLException e) { }
            try { if (stmt != null) stmt.close(); } catch (SQLException e) { }
            try { if (conn != null) conn.close(); } catch (SQLException e) { }
        }
    }
}
