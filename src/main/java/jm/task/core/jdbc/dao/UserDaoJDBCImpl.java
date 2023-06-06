package jm.task.core.jdbc.dao;

import com.mysql.cj.xdevapi.Collection;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserDaoJDBCImpl implements UserDao {

    private Connection dbConnection;
    private PreparedStatement statement;


    public UserDaoJDBCImpl() {
        try {
            dbConnection = new Util().getConnection();
        } catch (ClassNotFoundException | SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    public void createUsersTable() {
        try {
            statement = dbConnection.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS users(" +
                            "id INT NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                            "name VARCHAR(250) NOT NULL," +
                            "lastName VARCHAR(250) NOT NULL," +
                            "age TINYINT UNSIGNED NOT NULL);"
            );
            dbConnection.setAutoCommit(false);
            statement.execute();
            dbConnection.commit();
        } catch (SQLException sqlException) {
            try {
                dbConnection.rollback();
            } catch (SQLException sqlExceptionTransaction) {
                sqlExceptionTransaction.printStackTrace();
            }
            sqlException.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try {
            statement = dbConnection.prepareStatement(
                    "DROP TABLE IF EXISTS users CASCADE;"
            );
            dbConnection.setAutoCommit(false);
            statement.execute();
            dbConnection.commit();
        } catch (SQLException sqlException) {
            try {
                dbConnection.rollback();
            } catch (SQLException sqlExceptionTransaction) {
                sqlExceptionTransaction.printStackTrace();
            }
            sqlException.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            statement = dbConnection.prepareStatement(
                    "INSERT INTO users(name, lastname, age) VALUES (?,?,?);"
            );
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);
            dbConnection.setAutoCommit(false);
            statement.executeUpdate();
            dbConnection.commit();
        } catch (SQLException sqlException) {
            try {
                dbConnection.rollback();
            } catch (SQLException sqlExceptionTransaction) {
                sqlExceptionTransaction.printStackTrace();
            }
            sqlException.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try {
            statement = dbConnection.prepareStatement(
                    "DELETE FROM users WHERE id=?;"
            );
            statement.setLong(1, id);
            dbConnection.setAutoCommit(false);
            statement.execute();
            dbConnection.commit();
        } catch (SQLException sqlException) {
            try {
                dbConnection.rollback();
            } catch (SQLException sqlExceptionTransaction) {
                sqlExceptionTransaction.printStackTrace();
            }
            sqlException.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery("SELECT id, name, lastName,age FROM users");
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                users.add(user);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        try {
            statement = dbConnection.prepareStatement(
                    "DELETE FROM users;"
            );
            dbConnection.setAutoCommit(false);
            statement.execute();
            dbConnection.commit();
        } catch (SQLException sqlException) {
            try {
                dbConnection.rollback();
            } catch (SQLException sqlExceptionTransaction) {
                sqlExceptionTransaction.printStackTrace();
            }
            sqlException.printStackTrace();
        }
    }
}
