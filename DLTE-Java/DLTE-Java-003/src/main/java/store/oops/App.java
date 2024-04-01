package store.oops;

import java.util.Arrays;

/**
 * Hello world!
 *
 */
//public class App
//{
//    public static void main( String[] args )
//    {
//        System.out.println( "Hello World!" );
//    }
//}
class Traditional {
    static {
        System.out.println("CASA, Funds, Bonds, Loans");
        System.out.println("CLI banking");
        Traditional.main(new Integer[]{12,45,78});
        //Traditional.main(new String[]{"TietoEvry","Dlithe"});
    }
    static public int main(Integer[] args) {
        System.out.println("Mobile banking with bio-metric authentication");
        System.out.println(args.length);
        return 1;
    }
//    public static void main(String[] args) {
//        System.out.println(Arrays.toString(args));
//    }
}

class  App{
    public static void main(String[] args) {
        System.out.println("ATM, CDM, Passbook entry");
    }
}

class Device{
    public static void main(String[] args) {
        System.out.println("Internet banking and Wallets");
    }
}