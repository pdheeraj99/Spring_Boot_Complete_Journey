package com.security.learn.singleeventsourcing.events;

import java.time.LocalDateTime;

public abstract class Event {
    private String accountId;
    private LocalDateTime timestamp;

    public Event(String accountId) {
        this.accountId = accountId;
        this.timestamp = LocalDateTime.now();
    }

    public String getAccountId() { return accountId; }
    public LocalDateTime getTimestamp() { return timestamp; }
}