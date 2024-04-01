package org.example;

import oracle.jdbc.driver.OracleDriver;

import java.sql.*;
import java.util.ResourceBundle;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main(String[] args) {
        ResourceBundle resourceBundle=ResourceBundle.getBundle("appliation");
        try {
            Driver driver=new OracleDriver();
            DriverManager.registerDriver(driver);
            Connection connection=DriverManager.getConnection(resourceBundle.getString("db.url"),resourceBundle.getString("db.user"), resourceBundle.getString("db.pass"));
            PreparedStatement preparedStatement= connection.prepareStatement("select transaction_date from  transaction ");
            ResultSet resultSet=preparedStatement.executeQuery();
            while(resultSet.next()){
                System.out.println(resultSet.getObject(1));
            }
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
