package com.luciorim.inventoryservice;

import com.luciorim.inventoryservice.Repository.InventoryRepository;
import com.luciorim.inventoryservice.model.Inventory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
@Slf4j
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData(InventoryRepository inventoryRepository){
		return args -> {

			var inventories = List.of(
				new Inventory(null, "iphone12", 6),
				new Inventory(null, "ps5", 10)
			);

			log.info("Added mock data to DB: {}", inventories);

			inventoryRepository.saveAll(inventories);

		};
	}

}
