package com.luciorim.productservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class RequestUploadImageDto {

    @JsonProperty("image")
    @NotNull
    private MultipartFile image;

}
