package com.medilabosolutions.medilabo_notes.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "notes")
public class Note {

    @Id
    private String id;

    private String patId;

    private String patient;

    private String content;

    public Note(String patId, String patient, String content) {
        this.patId = patId;
        this.patient = patient;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPatientId() {
        return patId;
    }

    public void setPatientId(String patientId) {
        this.patId = patientId;
    }

    public String getPatName() {
        return patient;
    }

    public void setPatName(String patName) {
        this.patient = patName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
