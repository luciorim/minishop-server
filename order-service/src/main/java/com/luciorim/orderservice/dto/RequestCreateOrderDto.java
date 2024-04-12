package com.luciorim.orderservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class RequestCreateOrderDto {

    @NotNull
    @JsonProperty("products_to_check")
    private List<@Valid CheckProductDto> products;

}
