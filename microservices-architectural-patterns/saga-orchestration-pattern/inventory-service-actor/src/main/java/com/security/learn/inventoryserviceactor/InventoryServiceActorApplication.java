package com.security.learn.inventoryserviceactor;

import com.security.learn.inventoryserviceactor.entity.Inventory;
import com.security.learn.inventoryserviceactor.repo.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventoryServiceActorApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceActorApplication.class, args);
    }

    // This bean will run on application startup to load initial data
    @Bean
    public CommandLineRunner loadData(InventoryRepository repository) {
        return args -> {
            if (repository.findById("product-122").isEmpty()) {
                Inventory product = new Inventory("product-122", 100);
                repository.save(product);
                System.out.println("Loaded sample product-122 with 100 stock.");
            }
        };
    }

}
