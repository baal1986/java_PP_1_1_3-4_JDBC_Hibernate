package jm.task.core.jdbc;

import jm.task.core.jdbc.util.Util;

import java.io.IOException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try {
            System.out.printf(String.valueOf(new Util().getConnection().isClosed()));
        } catch (ClassNotFoundException | SQLException | RuntimeException | IOException ignored) {
            System.out.printf(String.valueOf(ignored.fillInStackTrace()));
        }

    }
}
