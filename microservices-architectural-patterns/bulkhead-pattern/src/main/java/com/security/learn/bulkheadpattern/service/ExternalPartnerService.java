package com.security.learn.bulkheadpattern.service;

import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ExternalPartnerService {

    private int attemptCounter = 0;

    @Retry(name = "invoiceServiceRetry", fallbackMethod = "invoiceFallback")
    public String getInvoiceDetails() throws IOException {
        attemptCounter++;
        System.out.println("🧾 Calling external invoice service... Attempt #" + attemptCounter);

        // Simulate a temporary failure for the first 2 attempts
        if (attemptCounter <= 2) {
            System.out.println("💥 Invoice service is temporarily down...");
            throw new IOException("Temporary network error");
        }

        // The 3rd attempt will succeed
        System.out.println("✅ Invoice service responded successfully!");

        // Reset for the next independent call
        int finalAttempt = attemptCounter;
        attemptCounter = 0;
        return "Invoice #INV12345 fetched successfully on attempt #" + finalAttempt;
    }

    // Fallback for when all retries have failed
    public String invoiceFallback(Exception ex) {
        attemptCounter = 0; // Reset counter on fallback
        System.out.println("❌ RETRY FALLBACK TRIGGERED - All attempts to fetch invoice failed. Error: " + ex.getMessage());
        return "Failed to fetch invoice details. Please try again later.";
    }
}