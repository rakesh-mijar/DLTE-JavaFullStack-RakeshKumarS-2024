package com.example.demojdbc;


import org.springframework.stereotype.Component;

import java.util.Date;
import javax.validation.constraints.*;
@Component
public class TransactionNew {
    @NotNull(message = "{id.not.null}")
    @Digits(integer = 5,fraction = 0)
    private  Long transactionId;
    @NotNull(message="{date.not.null}")
    private Date transactionDate;
    @NotBlank(message = "{name.not.null}")
    private String transactionBy;
    @NotBlank(message = "{name.not.null}")
    private String transactionTo;
    @NotNull(message = "{amount.not.null")
    @DecimalMin(value = "0.1",message = "{amount.not.zero}")
    private Double transactionAmount;
    @NotBlank(message = "{remarks.not.null}")
    private String transactionRemarks;


    public TransactionNew(@NotNull(message = "{id.not.null}") @Digits(integer = 5, fraction = 0) Long transactionId, @NotNull(message = "{date.not.null}") Date transactionDate, @NotBlank(message = "{name.not.null}") String transactionBy, @NotBlank(message = "{name.not.null}") String transactionTo, @NotNull(message = "{amount.not.null") @DecimalMin(value = "0.1", message = "{amount.not.zero}") Double transactionAmount, @NotBlank(message = "{remarks.not.null}") String transactionRemarks) {
        this.transactionId = transactionId;
        this.transactionDate = transactionDate;
        this.transactionBy = transactionBy;
        this.transactionTo = transactionTo;
        this.transactionAmount = transactionAmount;
        this.transactionRemarks = transactionRemarks;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getTransactionBy() {
        return transactionBy;
    }

    public void setTransactionBy(String transactionBy) {
        this.transactionBy = transactionBy;
    }

    public String getTransactionTo() {
        return transactionTo;
    }

    public void setTransactionTo(String transactionTo) {
        this.transactionTo = transactionTo;
    }

    public Double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(Double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public String getTransactionRemarks() {
        return transactionRemarks;
    }

    public void setTransactionRemarks(String transactionRemarks) {
        this.transactionRemarks = transactionRemarks;
    }

    public TransactionNew() {
    }
}
