package org.example;

import java.io.IOException;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class CreditCardAnalysis {
    static ResourceBundle resourceBundle=ResourceBundle.getBundle("application");
    static Logger logger= Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public static void main(String[] args) throws IOException {
        CreditCard[] myBank = {  //array of objects
                new CreditCard(8765678765678L, "Ramesh", new Date(2034, 12, 30), 555, 100000, new Date(2024, 3, 11), new Date(2024, 03, 30), 1111),
                new CreditCard(9876543L, "Suresh", new Date(2023, 02, 25), 432, 150000, new Date(2028, 4, 9), new Date(2025, 06, 26), 2345),
                new CreditCard(9876545678L, "Mahesh", new Date(2022, 05, 23), 987, 1234567, new Date(2026, 5, 1), new Date(2026, 04, 12), 5432)
        };
        Scanner scanner = new Scanner(System.in);
        CreditCardAnalysis analysis = new CreditCardAnalysis();

        //creating a log file
        FileHandler fileHandler=new FileHandler("credit-card-logs.txt",false);
        //SimpleFormatter simpleFormatter=new SimpleFormatter();
        //fileHandler.setFormatter(simpleFormatter);
        logger.addHandler(fileHandler);

        try{
            System.out.println(resourceBundle.getString("filter.limit"));
            System.out.println(resourceBundle.getString("enter.limit")); //Filter based on the given limit
            double limit = scanner.nextDouble();
            analysis.customersOnLimit(myBank, limit);
        }catch (MyBankException bankException){
            logger.log(Level.WARNING,bankException.toString());
            System.out.println("No data for specified limit"); //Filter based on the given limit

        }
        try{
            System.out.println(resourceBundle.getString("filter.date"));
            System.out.println(resourceBundle.getString("enter.date"));//Filter based on the date of bill payment
            int day = scanner.nextInt();
            analysis.customersOnDate(myBank, day);
        }catch(MyBankException dateException){
            logger.log(Level.WARNING,dateException.toString());
            System.out.println("No data for specified date");
        }

    }

    public void customersOnLimit(CreditCard[] customers, double limit) {//Filter based on the given limit
        for (CreditCard each : customers) {
            if (each.getCreditCardLimit() <= limit) {
                System.out.println(each.getCreditCardHolder() + " has limit " + each.getCreditCardLimit() + " lesser than specified limit" + limit);
            }
            else{
                throw new MyBankException();
            }
        }
    }

    public void customersOnDate(CreditCard[] customers, int day) {//Filter based on the date of bill payment
        for (CreditCard each : customers) {
            if (each.getDateOfBillPayement().getDay() <= day) {
                System.out.println(each.getCreditCardHolder() + " paid bill before or on the same day as specified day " + day);
            }
            else{
                throw new MyBankException();
            }
        }
    }
}