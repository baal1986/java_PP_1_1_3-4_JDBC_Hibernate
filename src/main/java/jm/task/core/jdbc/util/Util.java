package jm.task.core.jdbc.util;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Properties;


public class Util {
    private static final Properties properties = new Properties();
    private static String dataBaseDriver = properties.getProperty("db.driver");
    private static String dataBaseHost = properties.getProperty("db.host");
    private static String dataBaseLogin = properties.getProperty("db.login");
    private static String dataBasePassword = properties.getProperty("db.password");
    private static Optional<Connection> connection;

    public Util() throws IOException {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("database.properties")) {

            if (inputStream != null) {
                properties.load(inputStream);
            } else {
                throw new FileNotFoundException();
            }

            dataBaseDriver = properties.getProperty("db.driver");
            dataBaseHost = properties.getProperty("db.host");
            dataBaseLogin = properties.getProperty("db.login");
            dataBasePassword = properties.getProperty("db.password");
        }
    }

    private Optional<Connection> Connecting() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = Optional.ofNullable(
                DriverManager.getConnection(dataBaseHost, dataBaseLogin, dataBasePassword));
        return connection;
    }

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Connecting();
        return connection.isPresent() ? connection.get() : Connecting().orElse(null);
    }

    @Override
    public String toString() {
        return dataBaseDriver + " " + dataBaseHost + " " + dataBaseLogin + " " + dataBasePassword;
    }
}
