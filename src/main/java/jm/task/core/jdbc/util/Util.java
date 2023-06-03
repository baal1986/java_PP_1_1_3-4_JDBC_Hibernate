package jm.task.core.jdbc.util;

import java.util.Properties;

public class Util {
    private static final Properties properties = new Properties();
    private static final String dataBaseHost = properties.getProperty("db.host");
    private static final String dataBaseLogin = properties.getProperty("db.login");
    private static final String dataBasePassword = properties.getProperty("db.password");



}
