package lk.ijse.henHouse.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {
    private static DBConnection dbConnection;
    private Connection connection;
    private static final String URL =  "jdbc:mysql://localhost:3306/hen_shop";
    private static final Properties props = new Properties();

    static {
        props.setProperty("user","root");
        props.setProperty("password","1234");
    }

    private DBConnection() throws SQLException {
        connection = DriverManager.getConnection(URL,props);
    }

    public Connection getConnection(){
        return connection;
    }

    public static DBConnection getInstance() throws SQLException {
        if (dbConnection == null) {
            return dbConnection = new DBConnection();
        } else {
            return dbConnection;
        }
    }
    public static DBConnection getDbConnection() throws SQLException, ClassNotFoundException {
        return dbConnection == null ? dbConnection= new DBConnection() : dbConnection;
    }

}
