package com.luciorim.productservice.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "product")
public class Product {

    @MongoId
    private String id;

    @Field(name = "product_name")
    private String productName;

    @Field(name = "description")
    private String description;

    @Field(name = "product_price")
    private Long productPrice;

    @Field(name = "scu_code")
    private String sduCode;

    @Field(name = "enabled_quantity")
    private Integer enabledQuantity;

    @Field(name = "iamge_url")
    private String imageUrl;

}
