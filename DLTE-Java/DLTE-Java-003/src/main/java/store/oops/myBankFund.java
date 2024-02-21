package store.oops;

import java.util.Date;

//this class handles the instantiation of bond objects,manipulation of bond-data..
public class myBankFund {
    public static void main(String[] args) {
        myBankBond[] bonds = {
                new myBankBond(new Date(2023, 12, 12), 7.4, "Paid", "Samarth", 5),
                new myBankBond(new Date(2024, 5, 15), 5.5, "No Tax", "Bhuvan", 4),
                new myBankBond(new Date(2022, 7, 1), 3.9, "Not Paid", "Bharat", 3),
                new myBankBond(new Date(2023, 11, 28), 6.5, "Paid", "Bhagya", 7),
                new myBankBond(new Date(2022, 1, 3), 8.5, "No Tax", "Bhargav", 4),
        };
        myBankFund sort=new myBankFund();
        sort.fundWithHighestInterest(bonds);

    }
    public void fundWithHighestInterest(myBankBond[] bond){
        myBankBond var=null;
        for(int index=0;index<bond.length;index++){ //sorts the interest rate of customers in the descending order
            for(int nextIndex=index+1;nextIndex<bond.length;nextIndex++){
                if(bond[index].getInterestRate().compareTo(bond[nextIndex].getInterestRate())<0){
                    var=bond[index];
                    bond[index]=bond[nextIndex];
                    bond[nextIndex]=var;
                }
            }
        }
        for(myBankBond each:bond){   //to view the sorted list of customers
            System.out.println(each+" ");
        }
    }
}
