package com.security.learn.inventoryserviceactor.repo;

import com.security.learn.inventoryserviceactor.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, String> {
}