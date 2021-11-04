package by.hardzeyeu.libraryV2.connection;

import com.mysql.cj.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Class for connection initialization
 */

public class DBWorker {
    private static final String URL = "jdbc:mysql://localhost:3306/andersen";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    private Connection connection;

    public DBWorker() {
        try {
            Driver driver = new Driver();
            DriverManager.registerDriver(driver);
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void connectionClose() throws SQLException {
        connection.close();
    }
}
