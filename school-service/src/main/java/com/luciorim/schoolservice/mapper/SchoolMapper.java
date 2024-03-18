package com.luciorim.schoolservice.mapper;

import com.luciorim.schoolservice.dto.ResponseSchoolDto;
import com.luciorim.schoolservice.entities.School;
import org.springframework.stereotype.Component;

@Component
public class SchoolMapper {

    public ResponseSchoolDto toDto(School student){

        return ResponseSchoolDto.builder()
                .email(student.getEmail())
                .schoolName(student.getSchoolName())
                .build();

    }
}
