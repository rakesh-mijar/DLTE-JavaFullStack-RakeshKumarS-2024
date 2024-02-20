package store.oops;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditCard {
    private Long creditCardNumber;
    private String creditCardHolder;
    private Date creditCardExpiry;
    private Integer creditCardCVV;
    private Integer creditCardLimit;
    private Date dateOfBillGeneration;
    private Date dateOfBillPayement;
    private Integer CreditCardPin;
}
