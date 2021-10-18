package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String url = "jdbc:mysql://localhost:3306/users?autoReconnect=true&useSSL=false";
    private static final String userName = "root";
    private static final String password = "TT555bur";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(url, userName, password);
        } catch (SQLException e) {
            System.out.println("Error: no connection to DB.");
            e.printStackTrace();
        }
        return null;
    }
}
