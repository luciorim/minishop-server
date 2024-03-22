package com.luciorim.inventoryservice.Repository;


import com.luciorim.inventoryservice.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    List<Inventory> findInventoriesBySkuCodeIn(List<String> skuCodes);

}
