package com.luciorim.inventoryservice.Repository;


import com.luciorim.inventoryservice.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    Optional<Inventory> findInventoryBySkuCode(String skuCode);

}
