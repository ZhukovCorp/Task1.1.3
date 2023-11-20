package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        Transaction transaction = null;

        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            session
                    .createSQLQuery("Create table IF NOT EXISTS Users (id bigserial primary key," +
                            " name text not null, " +
                            "lastName text not null, " +
                            "age smallint not null)")
                    .executeUpdate();
            transaction.commit();

        } catch (HibernateException exception) {
            if (transaction != null) transaction.rollback();
            System.out.println(exception.getMessage());
        }

    }

    @Override
    public void dropUsersTable() {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            session.createSQLQuery("Drop table IF EXISTS Users").executeUpdate();
            transaction.commit();
        }
        catch (HibernateException exception){
            if (transaction != null) transaction.rollback();
            System.out.println(exception.getMessage());
        }
    }


    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()){
            transaction = session.getTransaction();
            transaction.begin();
            User user = new User(name, lastName, age);
            session.save(user);
            transaction.commit();
        }
        catch (HibernateException exception){

        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction =  null;

        try (Session session = Util.getSessionFactory().openSession()){
            transaction = session.getTransaction();
            transaction.begin();
            User user = session.load(User.class, id);
            session.delete(user);
            transaction.commit();
        }
        catch (HibernateException exception){
            if (transaction != null) transaction.rollback();
            System.out.println(exception.getMessage());
        }

    }

    @Override
    public List<User> getAllUsers() {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()){
            transaction = session.getTransaction();
            transaction.begin();
            List<User> usersList = session.createCriteria(User.class).list();
            transaction.commit();
            return usersList;
        }
        catch (HibernateException exception){
            if (transaction != null) transaction.rollback();
            System.out.println(exception.getMessage());
        }
        return null;
    }


    @Override
    public void cleanUsersTable() {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()){
            transaction = session.getTransaction();
            transaction.begin();
            session.createSQLQuery("delete from Users").executeUpdate();
            transaction.commit();
        }
        catch (HibernateException exception){
            if (transaction != null) transaction.rollback();
            System.out.println(exception.getMessage());
        }
    }
}

