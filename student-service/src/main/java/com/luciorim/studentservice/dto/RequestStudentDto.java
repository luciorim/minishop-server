package com.luciorim.studentservice.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RequestStudentDto {

    @JsonProperty("first_name")
    @NotNull
    private String firstname;

    @JsonProperty("last_name")
    @NotNull
    private String lastname;

    @JsonProperty("email")
    @NotNull
    private String email;

    @JsonProperty("school_id")
    @NotNull
    private Long schoolId;


}
