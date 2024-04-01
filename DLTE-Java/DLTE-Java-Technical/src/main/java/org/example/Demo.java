package org.example;

public class Demo{
    public static void main(String[] args){

    }
}

//
//import java.io.*;
//import java.util.ArrayList;
//import java.util.Scanner;
//
//public class Demo {
//    public static void main(String[] args){
//        EmployeeMain obj1=new EmployeeMain();
//        try{
//            System.out.println(obj1.getMiddleName().contains("Rakesh"));
//        }catch (NullPointerException nullexception){
//            System.out.println("Initialize the value...you'r trying to access "+nullexception.getMessage());
//        }
//    }
//}
//
//
//    public static void writeIntoFile(){
//        try{
//            FileOutputStream outputStream=new FileOutputStream("employee.doc",true);
//            ObjectOutputStream objectOutputStream=new ObjectOutputStream(outputStream);//writing java objects to an output stream
//            objectOutputStream.writeObject(employee);
//            objectOutputStream.writeObject(empaddress);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//    public static void readFromFile(){
//        try{
//            FileInputStream inputStream=new FileInputStream("employee.doc");
//            ObjectInputStream objectInputStream=new ObjectInputStream(inputStream);//reading java objects from an input stream
//            employee =(ArrayList<EmployeeMain>) objectInputStream.readObject();
//            empaddress=(ArrayList<EmployeeAddress>) objectInputStream.readObject();
//            System.out.println("Employee details read from file successfully!!");
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
//==========================
//public class BinaryReader {
//    public static void main(String[] args) throws IOException {
//        File file=new File("debits.doc");
//        FileInputStream fileInputStream=new FileInputStream(file);
//        InflaterInputStream inflaterInputStream=new InflaterInputStream(fileInputStream);
//
//        byte[] current=new byte[fileInputStream.available()];
//
//        inflaterInputStream.read(current);
//
//        System.out.println(new String(current));
//        inflaterInputStream.close();
//        fileInputStream.close();
//    }
//}
//=================================
//public class BinaryWrite {
//    public static void main(String[] args) throws IOException {
//        File file=new File("debits.doc");
//        FileOutputStream fileOutputStream=new FileOutputStream(file);
//        DeflaterOutputStream deflaterOutputStream=new DeflaterOutputStream(fileOutputStream);
//
//        DebitCard debitCard=new DebitCard(876567876567L,111,2121,new Date("2/11/2031"));
//
//        deflaterOutputStream.write(debitCard.toString().getBytes());
//
//        deflaterOutputStream.close();
//        fileOutputStream.close();
//    }
//}
//======================

//public void addToFile(EmployeeMain employee,EmployeeAddress empaddress){
//        readFromFile();
//         employeeMain.add(employee);
//         empAdrress.add(empaddress);
//        writeIntoFile();
//        logger.log(Level.INFO,user.getUserName()+resourceBundle.getString("user.saved"));
//        System.out.println(user.getUserName()+resourceBundle.getString("user.saved"));
//}

//check this older code
//package org.example;
//
//import sun.security.jgss.GSSCaller;
//
//import java.io.*;
//import java.util.*;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
//public class EmployeeDetails implements Serializable {
//    //static int count;
//    static ArrayList<EmployeeMain>  employee = new ArrayList<>();
//    static ArrayList<EmployeeAddress> empaddress=new ArrayList<>();
//    public static void main(String[] args){
//
//        //EmployeeMain employee=new EmployeeMain();
//        //EmployeeMain[] employee=null;//reference to the EmployeeMain[] where size is based on the no. of employees
//        //EmployeeAddress[] empaddress=null;//reference to the EmployeeDetails[] where size is based on the no. of employees
//        ResourceBundle resourceBundle=ResourceBundle.getBundle("application");
//        Logger logger=Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
//
//
//        EmployeeDetails details=new EmployeeDetails();
//        Scanner scanner=new Scanner(System.in);
//        int option=0;
//        while(true){
//            System.out.println(resourceBundle.getString("employee.greet"));
//            System.out.println(resourceBundle.getString("employee.menu"));
//            try{
//                option= scanner.nextInt();
//                switch(option){
//                    case 1://System.out.println("Enter the number of employees");
//                        //count=scanner.nextInt();
//                        //employee = new EmployeeMain[count];
//                        //empaddress = new EmployeeAddress[count];
//                        //readDetails(employee,empaddress);
//
//
//                        char choice='Y';
//
//                        EmployeeDetails.readDetails();
//                        break;
//                    case 2://displayEmpDetails(employee,empaddress);
//                        EmployeeDetails.displayEmpDetails();
//                        break;
//                    default:System.exit(0);
//                }
//            } catch (InputMismatchException ex) {
//                logger.log(Level.WARNING,"Invalid input!! Try again");
//                scanner.next();
//            }
//
//        }
//    }
//
//
//
//    //public static void readDetails(EmployeeMain[] employee,EmployeeAddress[] empaddress){
//    public static void readDetails(){
//        Scanner scanner=new Scanner(System.in);
//        char choice='Y';
//        do{
//            //for(int index=0;index<count;index++){
//            System.out.println("Enter the employee id");
//            Long empId=scanner.nextLong();
//            scanner.nextLine();
//            System.out.println("Enter employee first name");
//            String firstName=scanner.nextLine();
//            System.out.println("Enter employee middle name");
//            String middleName=scanner.nextLine();
//            System.out.println("Enter employee last name");
//            String lastName=scanner.nextLine();
//            System.out.println("Enter the mobile number");
//            Long empMobileNumber=scanner.nextLong();
//            scanner.nextLine();
//
//            //employee[index]=new EmployeeMain(firstName,middleName,lastName,empMobileNumber,empId);
//            employee.add(new EmployeeMain(firstName,middleName,lastName,empMobileNumber,empId));
//
//            //taking employee address using setters
//            EmployeeAddress address=new EmployeeAddress();
//            System.out.println("Enter the permanent address");
//            System.out.println("Enter the street or locality");
//            String permStreet=scanner.nextLine();
//            address.setPermStreet(permStreet);
//            System.out.println("Enter the State");
//            String permState=scanner.nextLine();
//            address.setPermState(permState);
//            System.out.println("Enter the Country");
//            String permCountry=scanner.nextLine();
//            address.setPermCountry(permCountry);
//            System.out.println("Enter the pincode");
//            String pincode=scanner.next();
//            address.setPermPincode(pincode);
//            scanner.nextLine();
//
//            System.out.println("Enter the temporary address");
//            System.out.println("Enter the street or locality");
//            String tempStreet=scanner.nextLine();
//            address.setTempStreet(tempStreet);
//            System.out.println("Enter the State");
//            String tempState=scanner.nextLine();
//            address.setTempState(tempState);
//            System.out.println("Enter the Country");
//            String tempCountry=scanner.nextLine();
//            address.setTempCountry(tempCountry);
//            System.out.println("Enter the pincode");
//            String tempPincode=scanner.next();
//            address.setTempPincode(tempPincode);
//
//            //empaddress[index]=address;
//            empaddress.add(address);
//
//            System.out.println("Do you want to enter another employee details? (Yes/No)");
//            choice=scanner.next().charAt(0);
//            scanner.nextLine();
//            //}
//        }while(choice == 'Y'||choice=='y');
//    }
//
//    //public static void displayEmpDetails(EmployeeMain[] employee,EmployeeAddress[] empaddress) {
//    public static void displayEmpDetails(){
//        int index=0;
//        for(EmployeeMain each:employee){
//            //EmployeeAddress address=empaddress[index];
//
//            EmployeeAddress address=empaddress.get(index);
//            System.out.println("Employee Details of "+(index+1));
//            //printing basic details using toString() method
//            System.out.println(each.toString());
//            //printing the address using getters and setters
//            System.out.println("Temporary address "+address.getTempStreet()+","+address.getTempState()+","+address.getTempCountry()+","+address.getPermPincode());
//            System.out.println("Permanent address "+address.getPermStreet()+","+address.getPermState()+","+address.getPermCountry()+","+address.getPermPincode());
//            System.out.println("\n");
//            index++;
//        }
//    }
//
//}
