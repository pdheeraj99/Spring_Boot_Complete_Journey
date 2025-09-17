package com.security.learn.singleeventsourcing.events;

public class AccountCreatedEvent extends Event {
    private String ownerName;

    public AccountCreatedEvent(String accountId, String ownerName) {
        super(accountId);
        this.ownerName = ownerName;
    }

    public String getOwnerName() { return ownerName; }
}