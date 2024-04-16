package com.luciorim.imageservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseUploadImageDto {
    @JsonProperty("image_url")
    private String imageUrl;
}
