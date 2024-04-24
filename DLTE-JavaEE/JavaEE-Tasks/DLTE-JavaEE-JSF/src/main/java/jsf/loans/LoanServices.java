package jsf.loans;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ManagedBean
@ApplicationScoped
public class LoanServices {
    private List<Loans> loans= new ArrayList<>();

    public List<Loans> getLoans() {
        return loans;
    }

    public void setLoans(List<Loans> loans) {
        this.loans = loans;
    }

    @PostConstruct
    public void init(){
        Loans[] loanlist={
                new Loans(12345L,100.0,"10/04/2024","closed","pranav",7865787678L),
                new Loans(54321L,2000.0,"21/03/2024","open","Raksha",9087678987L),
                new Loans(67890L,3000.0,"15/02/2024","closed","venkatesh",7867898789L),
        };
        loans.addAll(Stream.of(loanlist).collect(Collectors.toList()));
    }

    //method corresponding to adding the new loan
    public void createLoans(Loans loans1){
        try {
            loans.add(loans1);
            System.out.println("New loan insertion successfull!!");
        }catch (Exception ex){
            System.out.println(ex);
        }
    }


    //method corresponds to the deletion of loans based on loan number
    public void removeLoan(long loanNumber){
        loans.removeIf(loans->loans.getLoanNumber().equals(loanNumber));
        System.out.println("Deletion successful!!");
    }

    //method to display the closed loans
    public List<Loans> closedLoans(){
        List<Loans> filteredLoans=loans.stream().filter(loans1->loans1.getLoanStatus().equals("closed")).collect(Collectors.toList());
        filteredLoans.forEach(System.out::println);
        return filteredLoans;
    }

}
