package com.luciorim.schoolservice.controller;

import com.luciorim.schoolservice.dto.RequestSchoolDto;
import com.luciorim.schoolservice.dto.ResponseSchoolDto;
import com.luciorim.schoolservice.service.SchoolService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schools")
@RequiredArgsConstructor
public class SchoolController {

    private final SchoolService schoolService;

    @PostMapping
    public ResponseEntity<Void> createStudent(
            @RequestBody @Valid RequestSchoolDto requestSchoolDto){

        schoolService.createSchool(requestSchoolDto);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<ResponseSchoolDto>> getAllStudents(){

        return ResponseEntity
                .ok(schoolService.getAllSchools());

    }
}
