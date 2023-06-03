package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;
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
        connection = Optional.ofNullable(
                DriverManager.getConnection(dataBaseHost, dataBaseLogin, dataBasePassword));
        return connection;
    }

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        return connection.isPresent() ? connection.get() : Connecting().orElse(null);
    }
}
