package com.medilabosolutions.medilabo_front.dto;

import lombok.Data;

@Data
public class NoteDto {
    private String id;
    private String patId;
    private String patName;
    private String content;
}
