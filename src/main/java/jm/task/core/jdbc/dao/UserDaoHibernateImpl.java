package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private SessionFactory sessionFactory;

    public UserDaoHibernateImpl() {
        try {
            sessionFactory = new Util().getSessionFactory();
        } catch (SQLException | IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void createUsersTable() {

    }

    @Override
    public void dropUsersTable() {

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

    }

    @Override
    public void removeUserById(long id) {

    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            session.createSQLQuery(
                    "CREATE TABLE IF NOT EXISTS users(" +
                            "id INT NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                            "name VARCHAR(250) NOT NULL," +
                            "lastName VARCHAR(250) NOT NULL," +
                            "age TINYINT UNSIGNED NOT NULL);"
            ).addEntity(User.class);

            session.getTransaction().commit();
        } catch (HibernateException hibernateException) {
            hibernateException.printStackTrace();
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {

    }
}
