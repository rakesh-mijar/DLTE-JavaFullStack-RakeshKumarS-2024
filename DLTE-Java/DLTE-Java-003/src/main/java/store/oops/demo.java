package store.oops;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

//----------------------------implements runnable interface---------------------
//class myRunnable implements Runnable{
//    public void run(){
//        System.out.println("Thread is running for Runnable Implementation");
//    }
//    public void someMethod(){
//        System.out.println("Some method in Runnable");
//    }
//}
//class extendedRunnable extends myRunnable{
//    public void someOMethod(){
//        System.out.println("Some method in Runnable");
//    }
//}
//
//public class demo{
//    public static void main(String[] args){
//        myRunnable runnable=new myRunnable();
//        extendedRunnable runnable2=new extendedRunnable();
//       // Demo d2=new Demo();
//        runnable.run();
//       runnable2.someOMethod();
//    }
//}
//---------------------------extends thread class------------------------------------------
//class myThread extends Thread{
//    public void run(){
//        System.out.println("Thread is running for Runnable Implementation");
//    }
//    public void someMethod(){
//        System.out.println("Some method in Runnable");
//    }
//}
//class extendedThread extends myThread{
//    public void someOMethod(){
//        System.out.println("Some method in Runnable");
//    }
//}
//
//public class demo{
//    public static void main(String[] args){
//        myThread thread=new myThread();
//        extendedThread eThread=new extendedThread();
//        //eThread.start();
//        eThread.run();
//    }
//}
//==================use of throw keyword==================
//class Demo{
//    //defining a method
//    public static void checkNum(int num) {
//        if (num < 1) {
//            throw new ArithmeticException("\nNumber is negative, cannot calculate square");
//        }
//        else {
//            System.out.println("Square of " + num + " is " + (num*num));
//        }
//    }
//    //main method
//    public static void main(String[] args) {
////        Demo obj=new Demo();
////        obj.checkNum(-3);
//        Demo.checkNum(-3);
//        System.out.println("Rest of the code..");
//    }
//}

//===============use of throws keyword===================
//public class demo {
//    public static void main(String[] args) throws ArithmeticException,InputMismatchException{
//        Scanner scanner=new Scanner(System.in);
//        try {
//            System.out.println("Enter numerator");
//            int num=scanner.nextInt();
//            System.out.println("Enter denominator");
//            int deno=scanner.nextInt();
//            int result=num/deno;
//        }
//        catch (ArithmeticException e){
//            System.out.println("\nNumber cannot be divided by 0 "+e.getMessage());
//        }
//        catch (InputMismatchException ex){
//            System.out.println("\nInput mismatch "+ ex.getMessage());
//        }
//    }
//}
//=======================final keyword==================
//public class demo{
   // public static void main(String[] args){
      //  final int a=5;
   //     a=5;

//        final String str="Ram,SHam";
//        str="Suresh";
//        System.out.println(str);


//        final StringBuilder sb = new StringBuilder("Rakesh");
//        System.out.println(sb);
//        sb.append("ForRakesh");
//        System.out.println(sb);
  //
// }
//}
//class Demo2 extends Demo{
//
//}

//-------------overriding----------------
//class A
//{
//    final void m1()
//    {
//        System.out.println("This is a final method.");
//    }
//}
//class B extends A
//{
//    void m1()
//    {
//        // Compile-error! We can not override
//        System.out.println("Illegal!");
//    }
//}

//==================resources-----------------

//public class demo {
//    public static void main(String[] args) {
//        ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
//        Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
//        Scanner scanner = new Scanner(System.in);
//        try {
//            System.out.println("Enter the number");
//            int a = scanner.nextInt();
//            logger.log(Level.INFO, resourceBundle.getString("data.collected"));
//        } catch (InputMismatchException inexception) {
//            System.out.println(resourceBundle.getString("exception.input"));
//            logger.log(Level.WARNING, resourceBundle.getString("exception.input"));
//        }
//    }
//}
//==============generics=====================

public class demo{
public static void main(String[] args){
    //=====without generics========
    List list=new ArrayList();
    list.add(4);
    list.add("amma");
    System.out.println(list);

//    ========with generics==========
//    ArrayList<Integer> alist = new ArrayList<>();
//    alist.add(2);
//    alist.add("sharma");
//    System.out.println(list);
//
//    typecasting required without generics
//    ArrayList list=new ArrayList();
//    list.add("Hello");
//    String s= (String) list.get(0);
//    System.out.println(s);
//
//    typcasting not required with generics
//    ArrayList<Integer> list = new ArrayList<>();
//    list.add(50);
//    Integer a=list.get(0);
//    System.out.println(a);
}
}

//=======arraylist and vector=================
//public class demo{
//    public static void main(String[] args){
//        ArrayList<Double> aList=new ArrayList<>();
//        Vector<Double> vList=new Vector<>();
//
//        aList.add(75.3);aList.add(76.3);
//        Double a=aList.get(0);
//        System.out.println(a);
////        System.out.println(aList);
//        vList.addAll(aList);
//
//        aList.set(1,10001.10001);
//        System.out.println(vList.contains(4));
//
//        Collections.replaceAll(aList,76.3,99.9);
//        System.out.println(aList+"\n"+vList);
//        aList.removeAll(vList);
//        System.out.println(" " +aList);
//
//     stem.out.println(Collections.max(aList));
//        System.out.println(Collections.min(vList));
//    }
//}

//===========linkedlist=================
//public class demo{
//    public static void main(String[] args){
//        LinkedList<String> list=new LinkedList<>();
//        list.add("A");
//        list.add("B");
//        list.addFirst("C");
//        list.addLast("F");
//        System.out.println(list);
//        list.removeFirst();
//        list.removeLast();
//        System.out.println(list);
//
//    }
//}

//==========stack================
//class demo{
//    public static void main(String[] args){
//        Stack<String> str=new Stack<>();
//        str.push("A");
//        str.push("B");
//        str.push("C");
//        str.push("D");
//        System.out.println(str);
//        str.pop();
//        str.pop();
//        System.out.println(str);
//        System.out.println(str.peek());
//        str.clear();
//        System.out.println(str.isEmpty());
//    }
//}

//==========set=================

//public class demo{
//    public static void main(String[] args){
//        LinkedHashSet<Integer> set = new LinkedHashSet<>();
//        set.add(7);set.add(8);set.add(3);set.add(8);
//        System.out.println(set);
//
//
//        HashSet<String> set2=new HashSet<>();
//        //set2.addAll(set);
//        set2.add("That");set2.add("This");set2.add("Then");set2.add("There");
//        //System.out.println(set2);
//        Iterator<String> i=set2.iterator();
//        while(i.hasNext())
//        {
//            System.out.println(i.next());
//        }
//
//
//        TreeSet<String> set3=new TreeSet<>();
//        //set2.addAll(set);
//        //set3.add(8);set3.add(2);set3.add(6);set3.add(1);
//        set3.add("That");set3.add("This");set3.add("Then");set3.add("There");
//
//        System.out.println(set3);
//    }
//}

//==========map================
//
//public class demo{
//    public static void main(String[] args){
//        Hashtable<Integer,String> map=new Hashtable<>();
//        map.put(102,"Danesh");
//        map.put(100,"Mahesh");
//        map.put(101,"Suresh");
//        System.out.println(map);
//        for(Map.Entry<Integer, String> m:map.entrySet()){
//            System.out.println(m.getKey()+" "+m.getValue());
//        }
//        System.out.println("\n");
//        HashMap<Integer,Integer> map2=new HashMap<>();
//        map2.put(109,2);
//        map2.put(108,2);
//        map2.put(105,5);
//        //System.out.println(map);
//        for(HashMap.Entry<Integer, Integer> c:map2.entrySet()){
//            System.out.println(c.getKey()+" "+c.getValue());
//        }
//        System.out.println("\n");
//
//        TreeMap<Integer,String> map3=new TreeMap<>();
//        map3.put(109,"Ajay");
//        map3.put(108,null);
//        map3.put(105,"Sudha");
//        //System.out.println(map);
//        for(Map.Entry<Integer, String> d:map3.entrySet()){
//            System.out.println(d.getKey()+" "+d.getValue());
//        }
//
//
//    }
//}
//=================
//public class demo
//{
//    public SomeClass()
//    {
//        String s = GenMeth<>)();
//    }
//    public static void main(String[] args){
//        demo d=new demo();
//    }
//}