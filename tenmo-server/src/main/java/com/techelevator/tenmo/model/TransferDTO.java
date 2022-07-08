package com.techelevator.tenmo.model;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;

public class TransferDTO {

    @NotEmpty
    private String senderUsername;
    @NotEmpty
    private String recipientUsername;
    @NotEmpty
    private BigDecimal transferAmount;

    public String getSenderUsername() {
        return senderUsername;
    }

    public void setSenderUsername(String senderUsername) {
        this.senderUsername = senderUsername;
    }

    public String getRecipientUsername() {
        return recipientUsername;
    }

    public void setRecipientUsername(String recipientUsername) {
        this.recipientUsername = recipientUsername;
    }

    public BigDecimal getTransferAmount() {
        return transferAmount;
    }

    public void setTransferAmount(BigDecimal transferAmount) {
        this.transferAmount = transferAmount;
    }
}
