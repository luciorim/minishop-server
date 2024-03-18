package com.luciorim.schoolservice.service;

import com.luciorim.schoolservice.dto.RequestSchoolDto;
import com.luciorim.schoolservice.dto.ResponseSchoolDto;
import com.luciorim.schoolservice.entities.School;
import com.luciorim.schoolservice.mapper.SchoolMapper;
import com.luciorim.schoolservice.repository.SchoolRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class SchoolService {

    private final SchoolRepository schoolRepository;
    private final SchoolMapper schoolMapper;

    public void createSchool(RequestSchoolDto requestStudentDto){

        var school = School.builder()
                .email(requestStudentDto.getEmail())
                .schoolName(requestStudentDto.getSchoolName())
                .build();

        log.info("Created new school: {}", school);

        schoolRepository.save(school);

    }

    public List<ResponseSchoolDto> getAllSchools(){

        return schoolRepository
                .findAll()
                .stream()
                .map(schoolMapper::toDto)
                .collect(Collectors.toList());

    }


}
