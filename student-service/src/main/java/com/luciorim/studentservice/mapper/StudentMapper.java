package com.luciorim.studentservice.mapper;

import com.luciorim.studentservice.dto.ResponseStudentDto;
import com.luciorim.studentservice.entities.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {

    public ResponseStudentDto toDto(Student student){

        return ResponseStudentDto.builder()
                .email(student.getEmail())
                .firstname(student.getFirstname())
                .lastname(student.getLastname())
                .schoolId(student.getSchoolId())
                .build();

    }
}
