package com.luciorim.inventoryservice.service;

import com.luciorim.inventoryservice.Repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    @Transactional
    public Boolean isInStock(String skuCode){

        var inventory = inventoryRepository.findInventoryBySkuCode(skuCode);

        return inventory.filter(value -> value.getQuantity() > 0).isPresent();

    }

}
