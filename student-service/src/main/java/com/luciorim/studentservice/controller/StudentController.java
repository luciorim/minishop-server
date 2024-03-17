package com.luciorim.studentservice.controller;

import com.luciorim.studentservice.dto.RequestStudentDto;
import com.luciorim.studentservice.dto.ResponseStudentDto;
import com.luciorim.studentservice.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping
    public ResponseEntity<Void> createStudent(
            @RequestBody @Valid RequestStudentDto requestStudentDto){

        studentService.createStudent(requestStudentDto);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<ResponseStudentDto>> getAllStudents(){

        return ResponseEntity
                .ok(studentService.getAllStudents());

    }


}
