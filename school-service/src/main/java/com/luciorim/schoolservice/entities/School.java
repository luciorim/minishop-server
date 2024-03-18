package com.luciorim.schoolservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "students")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class School {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "school_name")
    private String schoolName;


    @Column(name = "email")
    private String email;


}
