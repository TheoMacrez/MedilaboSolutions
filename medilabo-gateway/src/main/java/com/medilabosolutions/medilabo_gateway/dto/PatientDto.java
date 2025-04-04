package com.medilabosolutions.medilabo_gateway.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PatientDto {
    private Integer id;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;  // correction ici
    private String gender;          // Enum sous forme de String JSON
    private String address;
    private String phoneNumber;     // correction ici
}

