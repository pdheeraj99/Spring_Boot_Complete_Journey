package com.security.learn.inventoryservice;

import com.security.learn.inventoryservice.entity.Inventory;
import com.security.learn.inventoryservice.repo.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }

    // This bean will run on application startup to load initial data
    @Bean
    public CommandLineRunner loadData(InventoryRepository repository) {
        return args -> {
            if (repository.findById("product-123").isEmpty()) {
                Inventory product = new Inventory("product-123", 100);
                repository.save(product);
                System.out.println("Loaded sample product-123 with 100 stock.");
            }
        };
    }

}
