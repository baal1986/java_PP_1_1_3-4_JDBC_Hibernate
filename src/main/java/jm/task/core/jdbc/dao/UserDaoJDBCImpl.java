package jm.task.core.jdbc.dao;

import com.mysql.cj.xdevapi.Collection;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class UserDaoJDBCImpl implements UserDao {

    private static Connection dbConnection;

    static {
        try {
            dbConnection = new Util().getConnection();
        } catch (ClassNotFoundException | SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    private Statement statement;
    private ResultSet resultSet;


    public UserDaoJDBCImpl() {


    }

    public void createUsersTable() {
        //"CREATE TABLE if not exists Grocery_bill (Employee_Id INT, Employee_name VARCHAR(50));"
        try {
            statement = dbConnection.createStatement();
            statement.executeUpdate(
                    "CREATE TABLE if not exists Users" +
                            " (User, Employee_name VARCHAR(50))"
            );

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public void dropUsersTable() {

    }

    public void saveUser(String name, String lastName, byte age) {

    }

    public void removeUserById(long id) {

    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try {
            statement = dbConnection.createStatement();
            resultSet = statement.executeQuery("SELECT id, name, lastName,age FROM Users");
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

    }
}
