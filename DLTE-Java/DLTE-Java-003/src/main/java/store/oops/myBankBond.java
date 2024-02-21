package store.oops;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/* this class focus on representing the properties and behaviour of bonds ---separate classes feature of Encapsulation */
import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Data //we need not look after getter and setter it is handled by lombok dependency
public class myBankBond {
    private Date maturity;
    private Double interestRate;
    private String taxStatus;
    private String bondHolder;
    private int period;

    public void fundWithHighestInterest(myBankBond[] bonds) {
    }
}
