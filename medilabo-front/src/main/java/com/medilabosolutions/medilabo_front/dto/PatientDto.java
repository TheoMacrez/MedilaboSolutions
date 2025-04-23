package com.medilabosolutions.medilabo_front.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

/**
 * DTO représentant les informations d'un patient.
 */
@Data
public class PatientDto {
    private Integer id;
    private String firstName;
    private String lastName;

    // Transformation du format pour la partie front
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    private String gender;
    private String address;
    private String phoneNumber;
}

