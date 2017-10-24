package includes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Constantine on 5/25/2017.
 */
public class MYSQL {
    public static Connection getConnection(){
        String connectionUrl = "jdbc:mysql://localhost:3306/p1";
        Connection connection = null;

        try{
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(connectionUrl, "root", "");
        }catch(Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
}