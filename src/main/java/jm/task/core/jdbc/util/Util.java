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
    private static String dataBaseHost;
    private static String dataBaseLogin;
    private static String dataBasePassword;
    private static Optional<Connection> connection;

    public Util() throws SQLException, ClassNotFoundException, IOException {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("database.properties")) {
            if (inputStream != null) {
                properties.load(inputStream);
            } else {
                throw new FileNotFoundException();
            }
            dataBaseHost = properties.getProperty("db.host");
            dataBaseLogin = properties.getProperty("db.login");
            dataBasePassword = properties.getProperty("db.password");
        }
        Connecting();
    }

    private Optional<Connection> Connecting() throws SQLException {
        return connection = Optional.ofNullable(
                DriverManager.getConnection(dataBaseHost, dataBaseLogin, dataBasePassword)
        );
    }

    public Connection getConnection() throws SQLException, IOException {
        return connection.isPresent() ? connection.get() : Connecting().orElse(null);
    }

}
