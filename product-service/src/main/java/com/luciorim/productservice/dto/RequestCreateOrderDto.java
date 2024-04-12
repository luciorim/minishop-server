package com.luciorim.productservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.UniqueElements;

import java.util.List;


@Data
@Builder
public class RequestCreateOrderDto {
    @JsonProperty("products_to_check")
    @UniqueElements
    private List<@Valid CheckProductDto> productsToCheck;
}
