package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.ConnectionManager;

import javax.persistence.Table;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        Connection connection = ConnectionManager.open();
        String table = "Create table IF NOT EXISTS users (id bigserial primary key," +
                " name text not null, " +
                "lastName text not null, " +
                "age smallint not null)";
        try{
            Statement statement = connection.createStatement();
        statement.executeQuery(table);
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
        }


    }

    public void dropUsersTable() {
        Connection connection = ConnectionManager.open();
        String delete = "Drop table IF EXISTS users";
        try {
            Statement statement = connection.createStatement();
            statement.executeQuery(delete);
        }
        catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        Connection connection = ConnectionManager.open();
        String insert = "insert into users values (default,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insert); // создаем стейтмент, который позволяет добавлять данные в запроос
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
        }



    }

    public void removeUserById(long id) {
        Connection connection = ConnectionManager.open();
        String remove = "delete from users where id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(remove);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        }
        catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    public List<User> getAllUsers() {
        Connection connection = ConnectionManager.open();
        String query = "Select * from users";
        List<User> users = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();  //Нужен для того, чтобы выполнить запрос и получить результат
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                long id = result.getLong("id");
                String name = result.getString("name");
                String lastName = result.getString("lastName");
                byte age = result.getByte("age");
                User user = new User(id, name, lastName, age);
                users.add(user);
            }
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
        }
        return users;

    }

    public void cleanUsersTable() {
        Connection connection = ConnectionManager.open();
        String clean = "delete from users";
        try {
            Statement statement = connection.createStatement();
            statement.executeQuery(clean);
        }
        catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }
}
