package com.rent.rentitnowfrontend.apis.backend.domain;

public class TransactionOption {

    private TransactionType transactionType;
    private String displayText;

    public TransactionOption(TransactionType transactionType, String displayText) {
        this.transactionType = transactionType;
        this.displayText = displayText;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public String getDisplayText() {
        return displayText;
    }

    @Override
    public String toString() {
        return displayText;
    }
}
