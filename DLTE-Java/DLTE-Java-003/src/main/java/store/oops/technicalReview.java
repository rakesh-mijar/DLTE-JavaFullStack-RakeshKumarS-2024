package store.oops;

import java.util.Scanner;

public class technicalReview {
    public static void main(String[] args){
        String empName="",empTempAddress="",empPermAddress="";
        System.out.println("Enter the employee name");
        Scanner scanner=new Scanner(System.in);
        empName=scanner.nextLine();
        System.out.println("Enter the temporary address");
        empTempAddress=scanner.nextLine();
        System.out.println("Enter the permanent address");
        empPermAddress=scanner.nextLine();
    }
}
