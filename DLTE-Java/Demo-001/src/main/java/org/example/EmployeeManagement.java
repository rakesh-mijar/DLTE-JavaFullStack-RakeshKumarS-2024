package org.example;

import org.readwrite.EmployeeManager;
import java.util.ResourceBundle;
import java.util.InputMismatchException;
import java.util.Scanner;

public class EmployeeManagement {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        EmployeeManager employeeManager = new EmployeeManager();


        int option;
        while (true) {
            System.out.println("---------------Welcome to My-Employee-Details Portal------------");
            System.out.println("Menu");
            System.out.println("1-Add Employee Details");
            System.out.println("2-Display Employee Details");
            System.out.println("3-Quit");
            System.out.println("Enter your choice");
            try {
                option = scanner.nextInt();
                switch (option) {
                    case 1:
                        employeeManager.addEmployee();
                        break;
                    case 2:
                        employeeManager.displayEmpDetails();
                        break;
                    case 3:
                        return;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.next();
            }
        }
    }
}


