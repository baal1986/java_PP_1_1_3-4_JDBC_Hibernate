package jm.task.core.jdbc;

import jm.task.core.jdbc.util.Util;

import java.io.IOException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try {
            Util util = new Util();
            System.out.println(util.toString());
            System.out.printf(String.valueOf(util.getConnection().isClosed()));
        } catch (ClassNotFoundException | SQLException | IOException ignored) {
            System.out.printf(String.valueOf(ignored.fillInStackTrace()));
        }

    }
}
