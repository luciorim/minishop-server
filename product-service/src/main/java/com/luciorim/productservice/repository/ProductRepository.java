package com.luciorim.productservice.repository;

import com.luciorim.productservice.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
    List<Product> findProductsBySduCodeIn(List<String> scuCode);
}
