package org.database;

import oracle.jdbc.driver.OracleDriver;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import org.middle.*;
public class DatabaseTarget implements StorageTarget {
    //private ResourceBundle resourceBundle=ResourceBundle.getBundle("database");
    private Connection connection;
    public DatabaseTarget(){
        try{
            Driver driver=new OracleDriver();
            DriverManager.registerDriver(driver);
            connection=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","demo","1234");
        }
        catch (SQLException sqlException){
            System.out.println(sqlException);
        }
    }

    @Override
    public EmployeeMediator getEmployee() {
        return new EmployeeDatabase(connection);
    }
}
