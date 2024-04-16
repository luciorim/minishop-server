package com.luciorim.productservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ResponseUploadImageDto {
    @JsonProperty("image_url")
    private String imageUrl;
}