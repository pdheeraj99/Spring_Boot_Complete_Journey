package com.security.learn.singleeventsourcing.domain;

import com.security.learn.singleeventsourcing.events.AccountCreatedEvent;
import com.security.learn.singleeventsourcing.events.Event;
import com.security.learn.singleeventsourcing.events.MoneyDepositedEvent;
import com.security.learn.singleeventsourcing.events.MoneyWithdrawnEvent;

import java.util.ArrayList;
import java.util.List;

public class BankAccountWithEvents {
    private String accountId;
    private String ownerName;
    private List<Event> events; // 🔥 This is the MAGIC! // ✅ Event history!

    public BankAccountWithEvents(String accountId, String ownerName) {
        this.accountId = accountId;
        this.ownerName = ownerName;
        this.events = new ArrayList<>();

        // First event! 🎉
        this.events.add(new AccountCreatedEvent(accountId, ownerName));
    }

    // 💰 Calculate balance from events - MIND BLOWN!
    /* calculate from events */
    public double getBalance() {
        double balance = 0.0;
        for (Event event : events) {
            if (event instanceof MoneyDepositedEvent) {
                balance += ((MoneyDepositedEvent) event).getAmount();
            } else if (event instanceof MoneyWithdrawnEvent) {
                balance -= ((MoneyWithdrawnEvent) event).getAmount();
            }
        }
        return balance;
    }

    // 💰 Deposit money - Creates MoneyDepositedEvent!
    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive! 😠");
        }

        // Create and store event! 🎉
        events.add(new MoneyDepositedEvent(accountId, amount));
    }

    // 💸 Withdraw money - Creates MoneyWithdrawnEvent!
    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive! 😠");
        }

        if (getBalance() < amount) {
            throw new IllegalArgumentException("Insufficient balance! 💸 Current: " + getBalance());
        }

        // Create and store event! 🎉
        events.add(new MoneyWithdrawnEvent(accountId, amount));
    }


    // Getters
    public String getAccountId() { return accountId; }
    public String getOwnerName() { return ownerName; }
    public List<Event> getEvents() { return new ArrayList<>(events); }
}
