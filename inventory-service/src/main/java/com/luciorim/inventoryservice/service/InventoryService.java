package com.luciorim.inventoryservice.service;

import com.luciorim.inventoryservice.Repository.InventoryRepository;
import com.luciorim.inventoryservice.dto.ResponseInventoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    @Transactional(readOnly = true)
    public List<ResponseInventoryDto> isInStock(List<String> skuCode){

       return inventoryRepository
                .findInventoriesBySkuCodeIn(skuCode)
                .stream()
                .map(inv ->
                        ResponseInventoryDto.builder()
                                .skuCode(inv.getSkuCode())
                                .enabledQuantity(inv.getQuantity())
                                .isInStock(inv.getQuantity() > 0)
                                .build()
                )
                .toList();

    }

}
