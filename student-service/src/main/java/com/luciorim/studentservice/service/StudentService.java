package com.luciorim.studentservice.service;

import com.luciorim.studentservice.dto.RequestStudentDto;
import com.luciorim.studentservice.dto.ResponseStudentDto;
import com.luciorim.studentservice.entities.Student;
import com.luciorim.studentservice.mapper.StudentMapper;
import com.luciorim.studentservice.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public void createStudent(RequestStudentDto requestStudentDto){

        var student = Student.builder()
                .email(requestStudentDto.getEmail())
                .firstname(requestStudentDto.getFirstname())
                .lastname(requestStudentDto.getLastname())
                .schoolId(requestStudentDto.getSchoolId())
                .build();

        log.info("Created new student: {}", student);

        studentRepository.save(student);

    }

    public List<ResponseStudentDto> getAllStudents(){

        return studentRepository
                .findAll()
                .stream()
                .map(studentMapper::toDto)
                .collect(Collectors.toList());

    }


}
