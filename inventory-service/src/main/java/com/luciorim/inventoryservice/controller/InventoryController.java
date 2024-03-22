package com.luciorim.inventoryservice.controller;

import com.luciorim.inventoryservice.dto.ResponseInventoryDto;
import com.luciorim.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/inventories")
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping()
    public ResponseEntity<List<ResponseInventoryDto>> isInStock(@RequestParam List<String> skuCodes){

        return ResponseEntity
                .ok(inventoryService.isInStock(skuCodes));

    }

}
