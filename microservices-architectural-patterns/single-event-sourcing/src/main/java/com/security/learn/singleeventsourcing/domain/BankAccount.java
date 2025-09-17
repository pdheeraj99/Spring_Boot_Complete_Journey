package com.security.learn.singleeventsourcing.domain;

public class BankAccount {
    private String accountId;
    private String ownerName;
    private double balance; // ❌ Static field

    public BankAccount(String accountId, String ownerName) {
        this.accountId = accountId;
        this.ownerName = ownerName;
        this.balance = 0.0;
    }

    // Getters
    public String getAccountId() { return accountId; }
    public String getOwnerName() { return ownerName; }
    public double getBalance() { return balance; }
}