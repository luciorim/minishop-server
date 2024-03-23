package com.luciorim.orderservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FallbackDto {

    @JsonProperty("message")
    private String message;

}
