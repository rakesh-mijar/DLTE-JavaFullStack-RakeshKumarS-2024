package branches.blocks;
//
//class Demo{
//    private demo2 exp;
//    private static class demo2 {
//        public int b=5;
//        public static void example(int a) {
//            System.out.println("Welcome to Tieto");
//        }
//    }
//    public Demo{
//        exp=new demo2();
//    }
//    public void example2(int ex){
//        demo2.example(ex);
//    }
//}
//public class demo {
//    public static void main(String[] args) {
//        System.out.println("Welcome to Tieto1");
//        Demo d = new Demo();
//        //d.example(2);
//        d.example2(5);
//    }
//}
//

//arrays
//public class demo{
//    public static void main(String[] args){
//        Object[] storage={"Ramesh",12.5,false,15000};
//        demo.findAndReplace(storage,"Ramesh","Suresh");
//    }
//    public static void findAndReplace(Object[] arr,Object oldValue,Object newValue){
//        for(int index=0;index<arr.length;index++){
//            if(arr[index].equals(oldValue)){
//                arr[index]=newValue;
//            }
//        }
//        for(int index=0;index<arr.length;index++){
//            System.out.println(arr[index]+"\t");
//        }
//    }
//}


//public class demo{
//
//    public static void main(String[] args){
//        String str1="GOLD,JSW";
//        String myStocks=new String("Wipro, Reliance, HP, Infosys");
//        myStocks=myStocks.concat(",Mic");
//        System.out.println(myStocks);
//        System.out.println(myStocks.substring(10,17));
//    }
//}


//// Abstract class
//abstract class Sunstar {
//    public void printInfo() {
//
//    }
//    //abstract void printInfo();
//}
//
//
//class Employee extends Sunstar {
//    public void printInfo()
//    {
//        String name = "avinash";
//        int age = 21;
//        float salary = 222.2F;
//
//        System.out.println(name);
//        System.out.println(age);
//        System.out.println(salary);
//    }
//}
//
//// Base class
//class demo {
//    public static void main(String args[])
//    {
//        //Sunstar s = new Employee();
//        s.printInfo();
//    }
//}