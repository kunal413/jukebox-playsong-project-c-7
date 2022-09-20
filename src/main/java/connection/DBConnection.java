package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public static Connection getConnection() {
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jukebox", "root", "kunal@1999");
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return con;
    }
}
