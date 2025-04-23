package com.medilabosolutions.medilabo_front.dto;

import lombok.Data;

/**
 * DTO représentant une note médicale liée à un patient.
 */
@Data
public class NoteDto {
    private String id;
    private String patId;
    private String content;
}
