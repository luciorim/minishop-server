package com.luciorim.productservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseProductsEnabledDto {
    private Boolean areAllEnabled;
}
