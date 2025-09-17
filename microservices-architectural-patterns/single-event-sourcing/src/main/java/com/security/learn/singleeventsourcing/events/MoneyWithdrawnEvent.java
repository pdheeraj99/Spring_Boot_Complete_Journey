package com.security.learn.singleeventsourcing.events;

public class MoneyWithdrawnEvent extends Event {
    private double amount;

    public MoneyWithdrawnEvent(String accountId, double amount) {
        super(accountId);
        this.amount = amount;
    }

    public double getAmount() { return amount; }
}