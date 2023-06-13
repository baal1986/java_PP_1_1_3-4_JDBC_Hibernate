package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDaoHibernateImpl implements UserDao {

    private SessionFactory sessionFactory;
    private Transaction transaction;

    public UserDaoHibernateImpl() {
        try {
            sessionFactory = new Util().getSessionFactory();
        } catch (HibernateException | SQLException | ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void createUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery(
                            "CREATE TABLE IF NOT EXISTS users(" +
                                    "id INT NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                                    "name VARCHAR(250) NOT NULL," +
                                    "lastName VARCHAR(250) NOT NULL," +
                                    "age TINYINT UNSIGNED NOT NULL);"
                    ).addEntity(User.class)
                    .executeUpdate();

            transaction.commit();
        } catch (HibernateException hibernateException) {
            try {
                transaction.rollback();
            } catch (HibernateException rollbackHibernateException) {
                rollbackHibernateException.printStackTrace();
            }
            hibernateException.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            session.createSQLQuery(
                            "DROP TABLE IF EXISTS users CASCADE;"
                    ).addEntity(User.class)
                    .executeUpdate();

            transaction.commit();
        } catch (HibernateException hibernateException) {
            try {
                transaction.rollback();
            } catch (HibernateException rollbackHibernateException) {
                rollbackHibernateException.printStackTrace();
            }
            hibernateException.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            User user = new User();
            user.setName(name);
            user.setLastName(lastName);
            user.setAge(age);
            session.save(user);

            transaction.commit();
        } catch (HibernateException hibernateException) {
            try {
                transaction.rollback();
            } catch (HibernateException rollbackHibernateException) {
                rollbackHibernateException.printStackTrace();
            }
            hibernateException.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            session.delete(session.get(User.class, id));

            transaction.commit();
        } catch (HibernateException hibernateException) {
            try {
                transaction.rollback();
            } catch (HibernateException rollbackHibernateException) {
                rollbackHibernateException.printStackTrace();
            }
            hibernateException.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            users = session.createQuery("FROM User")
                    .getResultList();

            transaction.commit();
        } catch (HibernateException hibernateException) {
            try {
                transaction.rollback();
            } catch (HibernateException rollbackHibernateException) {
                rollbackHibernateException.printStackTrace();
            }
            hibernateException.printStackTrace();
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            session.createQuery("DELETE User")
                    .executeUpdate();

            transaction.commit();
        } catch (HibernateException hibernateException) {
            try {
                transaction.rollback();
            } catch (HibernateException rollbackHibernateException) {
                rollbackHibernateException.printStackTrace();
            }
            hibernateException.printStackTrace();
        }
    }
}
