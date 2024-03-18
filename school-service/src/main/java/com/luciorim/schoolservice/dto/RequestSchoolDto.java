package com.luciorim.schoolservice.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RequestSchoolDto {

    @JsonProperty("school_name")
    @NotNull
    private String schoolName;

    @JsonProperty("email")
    @NotNull
    private String email;



}
