package store.oops;


import java.util.Date;

public class customerSupport {
    //CreditCard[] myBank=new CreditCard[10];
    public static void main(String[] args) {
        CreditCard[] myBank = {
                new CreditCard(8765678765678L,"Ramesh",new Date(2034,12,30),555,100000,new Date(2024,3,11),new Date(2024,03,30),1111),
                new CreditCard(9876543L,"Suresh",new Date(2023,02,25),432,150000,new Date(2028,4,9),new Date(2025,06,26),2345),
                new CreditCard(9876545678L,"Mahesh",new Date(2022,05,23),987,1234567,new Date(2026,5,1),new Date(2026,04,12),5432)
        };
        customerSupport support =new customerSupport();
        support.findearlyExpire(myBank);
        support.findBillDate(myBank,new Date(2022,04,24),new Date(2023,05,21));
    }
    public void findearlyExpire(CreditCard[] customers){
        for(CreditCard each:customers){
            if(each.getCreditCardExpiry().before(new Date(2022,11,25))){
                System.out.println(each.getCreditCardHolder()+" your credit card number "+each.getCreditCardNumber()+ " is to be upgraded ");
            }
        }
    }
    public void findBillDate(CreditCard[] customers,Date start,Date end){
        for(CreditCard each:customers){
            if(each.getDateOfBillGeneration().getDate()>=start.getDate() && each.getDateOfBillGeneration().getDate()<=end.getDate()){
                System.out.println(each.getCreditCardHolder()+" "+each.getDateOfBillGeneration());
            }
        }
    }

//    public void findBillDate(CreditCard[] customers,Date start,Date end){
//        for(CreditCard each:customers){
//            if(each.getDateOfBillGeneration().before(end)&&each.getDateOfBillGeneration().after(start))
//        }
//    }

}
