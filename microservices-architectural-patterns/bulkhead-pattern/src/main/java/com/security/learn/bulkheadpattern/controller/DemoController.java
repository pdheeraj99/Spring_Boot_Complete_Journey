package com.security.learn.bulkheadpattern.controller;

import com.security.learn.bulkheadpattern.service.ApiService;
import com.security.learn.bulkheadpattern.service.ExternalPartnerService;
import com.security.learn.bulkheadpattern.service.ReportService;
import com.security.learn.bulkheadpattern.service.SuperCriticalApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
public class DemoController {

    @Autowired
    private ReportService reportService;

    @Autowired
    private ApiService apiService;

    @Autowired
    private ExternalPartnerService externalPartnerService;

    @Autowired
    private SuperCriticalApiService superCriticalApiService;

    // Thread Pool Bulkhead Test - Heavy Reports
    @GetMapping("/report")
    public CompletableFuture<ResponseEntity<String>> generateReport() {
        try {
            return reportService.generateReport()
                    .thenApply(result -> ResponseEntity.ok(result));
        } catch (Exception e) {
            return CompletableFuture.completedFuture(
                    ResponseEntity.status(500).body("Report Error: " + e.getMessage())
            );
        }
    }

    // Semaphore Bulkhead Test - API Calls
    @GetMapping("/payment")
    public ResponseEntity<String> processPayment() {
        try {
            String response = apiService.callExternalApi();
            return ResponseEntity.ok(response);
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred.");
        }
    }

    // Fast Endpoint - Normal Tomcat Thread
    @GetMapping("/hello")
    public String hello() {
        String threadName = Thread.currentThread().getName();
        return "Hello World! Fast Response - Thread: " + threadName;
    }

    @GetMapping("/invoice")
    public String getInvoice() {
        try {
            // We try to call the method that might fail
            return externalPartnerService.getInvoiceDetails();
        } catch (Exception e) {
            // If, after all retries, it still fails, the fallback in the service
            // will be called. This catch block is for any other unexpected errors
            // and to satisfy the Java compiler.
            System.out.println("CONTROLLER: Exception caught - " + e.getMessage());
            return "An error occurred in the controller: " + e.getMessage();
        }
    }

    @GetMapping("/critical-api")
    public String callCriticalApi() {
        try {
            return superCriticalApiService.callApi();
        } catch (Exception e) {
            // This catches the RuntimeException thrown by the retryFallback
            return "CONTROLLER: Caught final failure from service. Circuit Breaker will take over soon.";
        }
    }

    // Health Check
    @GetMapping("/health")
    public String health() {
        return "Bulkhead Demo Application is Running!";
    }
}
