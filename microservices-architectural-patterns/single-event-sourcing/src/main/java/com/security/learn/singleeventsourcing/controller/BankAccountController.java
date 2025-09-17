package com.security.learn.singleeventsourcing.controller;
import com.security.learn.singleeventsourcing.domain.BankAccountWithEvents;
import com.security.learn.singleeventsourcing.events.Event;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
public class BankAccountController {

    // Simple in-memory storage (for demo!) 🧠
    private Map<String, BankAccountWithEvents> accounts = new HashMap<>();

    // 🏗️ Create Account
    @PostMapping("/create")
    public Map<String, Object> createAccount(@RequestBody Map<String, String> request) {
        String accountId = request.get("accountId");
        String ownerName = request.get("ownerName");

        BankAccountWithEvents account = new BankAccountWithEvents(accountId, ownerName);
        accounts.put(accountId, account);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Account created successfully! 🎉");
        response.put("accountId", accountId);
        response.put("balance", account.getBalance());
        return response;
    }

    // 💰 Deposit Money
    @PostMapping("/{accountId}/deposit")
    public Map<String, Object> deposit(@PathVariable String accountId,
                                       @RequestBody Map<String, Double> request) {
        BankAccountWithEvents account = accounts.get(accountId);
        if (account == null) {
            throw new RuntimeException("Account not found! 😢");
        }

        double amount = request.get("amount");
        account.deposit(amount);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Money deposited successfully! 💰");
        response.put("amount", amount);
        response.put("newBalance", account.getBalance());
        return response;
    }

    // 💸 Withdraw Money
    @PostMapping("/{accountId}/withdraw")
    public Map<String, Object> withdraw(@PathVariable String accountId,
                                        @RequestBody Map<String, Double> request) {
        BankAccountWithEvents account = accounts.get(accountId);
        if (account == null) {
            throw new RuntimeException("Account not found! 😢");
        }

        double amount = request.get("amount");
        account.withdraw(amount);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Money withdrawn successfully! 💸");
        response.put("amount", amount);
        response.put("newBalance", account.getBalance());
        return response;
    }

    // 📊 Get Balance
    @GetMapping("/{accountId}/balance")
    public Map<String, Object> getBalance(@PathVariable String accountId) {
        BankAccountWithEvents account = accounts.get(accountId);
        if (account == null) {
            throw new RuntimeException("Account not found! 😢");
        }

        Map<String, Object> response = new HashMap<>();
        response.put("accountId", accountId);
        response.put("ownerName", account.getOwnerName());
        response.put("balance", account.getBalance());
        response.put("totalEvents", account.getEvents().size());
        return response;
    }

    // 📚 Get Event History - THE MAGIC! ✨
    @GetMapping("/{accountId}/events")
    public Map<String, Object> getEvents(@PathVariable String accountId) {
        BankAccountWithEvents account = accounts.get(accountId);
        if (account == null) {
            throw new RuntimeException("Account not found! 😢");
        }

        List<Event> events = account.getEvents();

        Map<String, Object> response = new HashMap<>();
        response.put("accountId", accountId);
        response.put("totalEvents", events.size());
        response.put("events", events);
        response.put("currentBalance", account.getBalance());
        return response;
    }
}
