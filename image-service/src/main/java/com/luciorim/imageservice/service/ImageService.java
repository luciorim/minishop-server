package com.luciorim.imageservice.service;

import com.amazonaws.services.s3.AmazonS3;
import com.luciorim.imageservice.dto.ResponseUploadImageDto;
import com.luciorim.imageservice.validators.ValidFile;
import jakarta.ws.rs.BadRequestException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ImageService {

    @Value("${aws.s3.bucket}")
    private String bucketName;

    private final AmazonS3 s3;

    public ResponseUploadImageDto uploadImage(@ValidFile MultipartFile file) {
        try {
            if (file.isEmpty() || file.getOriginalFilename() == null) {
                log.error("Empty file has been uploaded");
                throw new BadRequestException("Empty file has been uploaded");
            }
            String uniqueFileName = UUID.randomUUID() + "." + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            File fileToUpload = convertMultipartToFile(file);

            s3.putObject(bucketName, uniqueFileName, fileToUpload);
            var url = s3.getUrl(bucketName, uniqueFileName);

            return ResponseUploadImageDto.builder()
                    .imageUrl(url.toString())
                    .build();
        }catch (Exception e){
            log.error("IOException: ", e);
            throw new BadRequestException("Failed to store files");
        }
    }

    public File convertMultipartToFile(MultipartFile file) throws IOException {
        File convertedFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
        try {
            Files.copy(file.getInputStream(), convertedFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return convertedFile;
    }
}
