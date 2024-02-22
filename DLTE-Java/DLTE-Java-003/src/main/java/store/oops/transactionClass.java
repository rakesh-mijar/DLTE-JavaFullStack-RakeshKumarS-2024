package store.oops;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class transactionClass {
    private Date dateOfTransaction;
    private Double transactionAmount;
    private String reciept;
    private String remarks;

    public void transactionWithinRange(transactionClass[] money, int startDay, int endDay) {
    }

    public void leastTransaction(transactionClass[] transaction) {
    }

    public void maxTransaction(transactionClass[] transaction) {
    }

    public void remarksTransaction(transactionClass[] transaction, String remarks) {
    }

    public void beneficiaryTransaction(transactionClass[] transaction, String benificiaryName) {
    }

    public void sortingBeneficiary(transactionClass[] transaction) {
    }

    public void sortingAmount(transactionClass[] transaction) {
    }
}
