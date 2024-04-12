package com.luciorim.orderservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.nio.DoubleBuffer;
import java.util.List;
import java.util.Map;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_number")
    private String orderNumber;

    @Column(name = "total_price")
    private Double totalPrice;

    @MapKeyColumn(name = "product_id")
    @Column(name = "quantity")
    @ElementCollection
    private Map<String, Integer> orderItems;

}
