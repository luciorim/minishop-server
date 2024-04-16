package com.luciorim.imageservice.controller;

import com.luciorim.imageservice.dto.RequestUploadImageDto;
import com.luciorim.imageservice.dto.ResponseUploadImageDto;
import com.luciorim.imageservice.service.ImageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController("api/images")
public class ImageController {

    private final ImageService imageService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ResponseUploadImageDto> upload(@ModelAttribute @Valid RequestUploadImageDto requestUploadImageDto){
        return ResponseEntity.ok(imageService.uploadImage(requestUploadImageDto.getImage()));
    }

}
