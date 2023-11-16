package jm.task.core.jdbc.util;


import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

import static java.sql.DriverManager.getConnection;

public class Util {
    public static void main(String[] args) throws SQLException {
        Class<Driver> driverclass = Driver.class;
       try (var connection = ConnectionManager.open()){
           System.out.println(connection.getTransactionIsolation());
       }
    }
}
