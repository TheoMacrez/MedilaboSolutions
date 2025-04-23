package com.medilabosolutions.medilabo_diabetes_assessment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

/**
 * DTO représentant les informations d'un patient.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientDto {
    private int id;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private Gender gender;

}
