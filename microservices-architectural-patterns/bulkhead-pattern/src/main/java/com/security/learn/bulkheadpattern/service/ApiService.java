package com.security.learn.bulkheadpattern.service;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Random;

@Service
public class ApiService {

    private final Random random = new Random();

    // Semaphore Bulkhead - API Call Protection
    @Bulkhead(name = "apiService", type = Bulkhead.Type.SEMAPHORE, fallbackMethod = "apiFallback")
    public String callExternalApi() throws InterruptedException {
        String threadName = Thread.currentThread().getName();
        String startTime = LocalDateTime.now().toString();

        System.out.println("🌐 API CALL STARTED - Thread: " + threadName + " - Time: " + startTime);

        // Simulate external API call (2-5 seconds random)
        // Fixed: Much shorter delay
        int apiDelay = 2000 + random.nextInt(3000); // 2-5 seconds (not 20-23!)

        Thread.sleep(apiDelay);

        String endTime = LocalDateTime.now().toString();
        System.out.println("✅ API CALL COMPLETED - Thread: " + threadName + " - Time: " + endTime);

        return "External API Response Success! Thread: " + threadName + " (Delay: " + apiDelay + "ms)";
    }

    // Fallback method - when semaphore limit exceeded
    public String apiFallback(Exception ex) {
        String threadName = Thread.currentThread().getName();
        System.out.println("❌ API FALLBACK TRIGGERED - Thread: " + threadName + " - " + ex.getMessage());
        return "API service is busy! Using cached data. Thread: " + threadName;
    }
}
