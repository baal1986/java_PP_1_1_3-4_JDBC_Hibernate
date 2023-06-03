package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Properties;

public class Util {
    private static final Properties properties = new Properties();
    private static final String dataBaseHost = properties.getProperty("db.host");
    private static final String dataBaseLogin = properties.getProperty("db.login");
    private static final String dataBasePassword = properties.getProperty("db.password");
    private static Optional<Connection> connection;

    private static Optional<Connection> Connecting() throws ClassNotFoundException, SQLException {
        Class.forName("jm.task.mysql.Driver");
        return Optional.ofNullable(
                DriverManager.getConnection(dataBaseHost, dataBaseLogin, dataBasePassword));
    }

    public static Optional<Connection> getConnection() throws ClassNotFoundException, SQLException {
        return connection.isPresent() ? connection : Connecting();
    }
}
