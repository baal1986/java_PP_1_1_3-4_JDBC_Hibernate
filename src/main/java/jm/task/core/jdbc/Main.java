package jm.task.core.jdbc;

import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try {
            System.out.printf(new Util().toString());
            System.out.printf(String.valueOf(Util.getConnection().isClosed()));
        } catch (ClassNotFoundException | SQLException ignored) {

        }

    }
}
