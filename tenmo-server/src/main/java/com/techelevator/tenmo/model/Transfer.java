package com.techelevator.tenmo.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Transfer {

    private Long transferId;
    private Long senderId;
    private Long recipientId;
    private LocalDate timestamp = LocalDate.now();
    private String transferStatus;
    private BigDecimal transferAmount;

    public Transfer () { }

    public Transfer(Long transferId, Long senderId, Long recipientId, LocalDate timestamp, String transferStatus, BigDecimal transferAmount){
        this.transferId = transferId;
        this.senderId = senderId;
        this.recipientId = recipientId;
        this.timestamp = timestamp;
        this.transferStatus = "Approved";
        this.transferAmount = transferAmount;
    }

    public void setTransferId(Long transferId) {
        this.transferId = transferId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public void setRecipientId(Long recipientId) {
        this.recipientId = recipientId;
    }

    public void setTimestamp(LocalDate timestamp) {
        this.timestamp = timestamp;
    }

    public void setTransferAmount(BigDecimal transferAmount) {
        this.transferAmount = transferAmount;
    }

    public void setTransferStatus(String transferStatus) {
        this.transferStatus = transferStatus;
    }

    public Long getTransferId() {
        return transferId;
    }

    public Long getSenderId() {
        return senderId;
    }

    public Long getRecipientId() {
        return recipientId;
    }

    public LocalDate getTimestamp() {
        return timestamp;
    }

    public String getTransferStatus() {
        return transferStatus;
    }

    public BigDecimal getTransferAmount() {
        return transferAmount;
    }
}
