package com.security.learn.bulkheadpattern.service;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class SuperCriticalApiService {

    // All three annotations protecting this method
    @CircuitBreaker(name = "superCriticalApiCb", fallbackMethod = "cbFallback")
    @Retry(name = "superCriticalApiRetry", fallbackMethod = "retryFallback")
    @Bulkhead(name = "superCriticalApiBulkhead", type = Bulkhead.Type.SEMAPHORE)
    public String callApi() throws IOException {
        System.out.println("⚠️ Attempting to call the super critical API...");
        // This service is permanently down for our test
        throw new IOException("Permanent Failure: Service is down");
    }

    // Fallback for Retry (called after all retries fail)
    public String retryFallback(IOException ex) {
        System.out.println("❌ RETRY FALLBACK: All attempts failed. Reporting failure to Circuit Breaker.");
        // Re-throw exception to make the Circuit Breaker count this as a failure
        throw new RuntimeException("Service is still down after all retries", ex);
    }

    // Fallback for Circuit Breaker (called when the circuit is OPEN)
    public String cbFallback(Exception ex) {
        System.out.println("⛔ CIRCUIT BREAKER FALLBACK: Gate is locked. No more calls.");
        return "SERVICE IS UNAVAILABLE. Please try again after a long time.";
    }
}