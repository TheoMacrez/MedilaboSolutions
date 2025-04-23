package com.medilabosolutions.medilabo_diabetes_assessment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

/**
 * DTO représentant une note médicale liée à un patient.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoteDto {
    private String id;
    private int patId;
    private String content;
}
