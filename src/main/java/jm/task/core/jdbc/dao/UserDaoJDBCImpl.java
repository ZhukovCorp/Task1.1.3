package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        String table = "Create table IF NOT EXISTS users (id bigserial primary key," +
                " name text not null, " +
                "lastName text not null, " +
                "age smallint not null)";
        try (
                Connection connection = Util.open();
                Statement statement = connection.createStatement();
        ) {
            statement.executeQuery(table);


        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }

    }

    public void dropUsersTable() {

        String delete = "Drop table IF EXISTS users";
        try (
                Connection connection = Util.open();
                Statement statement = connection.createStatement()
        ) {
            statement.executeQuery(delete);
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
    }

    public void saveUser(String name, String lastName, byte age) {

        String insert = "insert into users values (default,?,?,?)";
        try (
                Connection connection = Util.open();
                PreparedStatement preparedStatement = connection.prepareStatement(insert) // создаем стейтмент, который позволяет добавлять данные в запроос
        ) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }


    }

    public void removeUserById(long id) {
        String remove = "delete from users where id = ?";
        try (
                Connection connection = Util.open();
                PreparedStatement preparedStatement = connection.prepareStatement(remove)
        ) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
    }

    public List<User> getAllUsers() {
        String query = "Select * from users";
        List<User> users = new ArrayList<>();
        try (
                Connection connection = Util.open();
                Statement statement = connection.createStatement() //Нужен для того, чтобы выполнить запрос и получить результат
        ) {
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                long id = result.getLong("id");
                String name = result.getString("name");
                String lastName = result.getString("lastName");
                byte age = result.getByte("age");
                User user = new User(name, lastName, age);
                user.setId(id);
                users.add(user);
            }
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
        return users;

    }

    public void cleanUsersTable() {

        String clean = "delete from users";
        try (
                Connection connection = Util.open();
                Statement statement = connection.createStatement()
        ) {
            statement.executeQuery(clean);
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
    }

    public String toString() {
        List<User> e = getAllUsers();
        String s = new String();
        for (User user : e) {
            s += user + "\n";
        }
        System.out.println(e);
        return s;
    }

}
