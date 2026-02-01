package utils;
import java.sql.*;
public class DatabaseConnection {
    private static final String URL = "jdbc:postgresql://localhost:5434/library_db";
    private static final String USER = "postgres";
    private static final String PASS = "1234";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException e) {
            throw new RuntimeException("DB connection failed! " + e.getMessage());
        }
    }

}
