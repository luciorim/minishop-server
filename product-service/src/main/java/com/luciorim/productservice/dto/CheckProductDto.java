package com.luciorim.productservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@Builder
@EqualsAndHashCode(of = "scuCode")
public class CheckProductDto {

    @JsonProperty("scu_code")
    @NotNull
    private String scuCode;

    @JsonProperty("price")
    private Double price;

    @JsonProperty("needed_quantity")
    @NotNull
    @Positive
    private Integer neededQuantity;
}
