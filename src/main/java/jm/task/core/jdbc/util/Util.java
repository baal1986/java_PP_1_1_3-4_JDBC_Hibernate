package jm.task.core.jdbc.util;


import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

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
    private static SessionFactory sessionFactory;


    public Util() throws SQLException, ClassNotFoundException, HibernateException, IOException {
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

        properties.clear();
        ClassLoader classLoader = getClass().getClassLoader();
        //Properties properties = new Properties();
        properties.load(classLoader.getResourceAsStream("hibernate.properties"));
        sessionFactory = new Configuration()
                .addProperties(properties)
                .configure()
                .addAnnotatedClass(jm.task.core.jdbc.model.User.class)
                .buildSessionFactory();
    }

    private Optional<Connection> Connecting() throws SQLException {
        return connection = Optional.ofNullable(
                DriverManager.getConnection(dataBaseHost, dataBaseLogin, dataBasePassword)
        );
    }

    public Connection getConnection() throws SQLException, IOException {
        return connection.isPresent() ? connection.get() : Connecting().orElse(null);
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
