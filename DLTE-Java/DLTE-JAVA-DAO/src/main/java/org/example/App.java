package org.example;

import java.sql.Date;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        StorageTarget storageTarget=new DatabaseTarget();

        UserServices userServices=new UserServices(storageTarget);

        //User user=new User("sanad@123","sunsanath","1-89,naindahalli","sanath@gmail.com",2345553L,10000.0);
        //userServices.callSave(user);
        //System.out.println(userServices.callFindById("sanat@123"));
        System.out.println(userServices.callfindByUserAndDate("rak@123",Date.valueOf("2024-03-14")));
       //System.out.println(userServices.callFindById("ra@123"));
    }
}
