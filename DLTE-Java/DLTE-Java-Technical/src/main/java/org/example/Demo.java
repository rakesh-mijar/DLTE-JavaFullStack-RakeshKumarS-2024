package org.example;

import java.util.Scanner;

public class Demo {
    public static void main(String[] args){
        EmployeeMain obj1=new EmployeeMain();
        try{
            System.out.println(obj1.getMiddleName().contains("Rakesh"));
        }catch (NullPointerException nullexception){
            System.out.println("Initialize the value...you'r trying to access "+nullexception.getMessage());
        }
    }
}
