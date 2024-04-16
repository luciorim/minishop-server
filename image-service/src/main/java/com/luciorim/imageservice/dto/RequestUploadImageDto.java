package com.luciorim.imageservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
public class RequestUploadImageDto {

    @JsonProperty("image")
    @NotNull
    private MultipartFile image;

}
