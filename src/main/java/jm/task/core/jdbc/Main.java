package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserDaoJDBCImpl userDao= new UserDaoJDBCImpl();
        userDao.createUsersTable();
        userDao.saveUser("Barack", "Obama", (byte) 62);
        userDao.saveUser("Kassym-Jomart", "Tokayev", (byte) 70);
        userDao.saveUser("Ilham", "Aliyev", (byte) 61);
        userDao.saveUser("Alexander", "Lukashenko", (byte) 69);
        userDao.getAllUsers();
        System.out.println(userDao);
        userDao.cleanUsersTable();
        userDao.dropUsersTable();




    }
}
