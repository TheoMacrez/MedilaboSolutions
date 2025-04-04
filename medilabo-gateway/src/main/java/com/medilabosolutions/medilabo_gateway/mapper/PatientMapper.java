package com.medilabosolutions.medilabo_gateway.mapper;

import com.medilabosolutions.medilabo_gateway.dto.PatientDto;
import com.medilabosolutions.medilabo_patient.model.Gender;
import com.medilabosolutions.medilabo_patient.model.Patient;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PatientMapper {

    // Convertir un Patient (backend) en PatientDto (gateway)
    public PatientDto toDto(Patient patient) {
        PatientDto dto = new PatientDto();
        dto.setId(patient.getId());
        dto.setFirstName(patient.getFirstName());
        dto.setLastName(patient.getLastName());
        dto.setDateOfBirth(patient.getDateOfBirth());
        dto.setGender(patient.getGender().name()); // Convertir Enum en String
        dto.setAddress(patient.getAddress());
        dto.setPhoneNumber(patient.getPhoneNumber());
        return dto;
    }

    // Convertir un PatientDto (gateway) en Patient (backend)
    public Patient toEntity(PatientDto dto) {
        Patient patient = new Patient();
        patient.setId(dto.getId());
        patient.setFirstName(dto.getFirstName());
        patient.setLastName(dto.getLastName());
        patient.setDateOfBirth(dto.getDateOfBirth());
        patient.setGender(Gender.valueOf(dto.getGender())); // Convertir String en Enum
        patient.setAddress(dto.getAddress());
        patient.setPhoneNumber(dto.getPhoneNumber());
        return patient;
    }
}
