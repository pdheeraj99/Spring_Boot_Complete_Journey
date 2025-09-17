package com.security.learn.bulkheadpattern.service;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;

@Service
public class ReportService {

    // FIXED: Returns CompletableFuture for ThreadPool Bulkhead
    @Bulkhead(name = "reportService", type = Bulkhead.Type.THREADPOOL, fallbackMethod = "reportFallback")
    public CompletableFuture<String> generateReport() throws InterruptedException {
        String threadName = Thread.currentThread().getName();
        String startTime = LocalDateTime.now().toString();

        System.out.println("🔥 REPORT STARTED - Thread: " + threadName + " - Time: " + startTime);

        // Simulate heavy report generation
        Thread.sleep(15000);

        String endTime = LocalDateTime.now().toString();
        System.out.println("✅ REPORT COMPLETED - Thread: " + threadName + " - Time: " + endTime);

        String result = "Heavy Report Generated Successfully! Thread: " + threadName;

        // RETURN CompletableFuture!
        return CompletableFuture.completedFuture(result);
    }

    // Fallback method - MUST also return CompletableFuture
    public CompletableFuture<String> reportFallback(Exception ex) {
        System.out.println("❌ REPORT FALLBACK TRIGGERED - " + ex.getMessage());
        String fallbackResult = "Report service is busy! Please try again later. Error: " + ex.getMessage();
        return CompletableFuture.completedFuture(fallbackResult);
    }
}
