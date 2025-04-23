package com.medilabosolutions.medilabo_notes.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Représente une note médicale associée à un patient dans une base de données MongoDB.
 */
@Document(collection = "notes")
public class Note {

    /**
     * Identifiant unique de la note.
     */
    @Id
    private String id;

    /**
     * Identifiant du patient auquel cette note est associée.
     */
    private String patId;

    /**
     * Contenu textuel de la note.
     */
    private String content;

    /**
     * Constructeur sans argument.
     */
    public Note() {
    }

    /**
     * Constructeur avec paramètres.
     *
     * @param patId identifiant du patient
     * @param content contenu de la note
     */
    public Note(String patId, String content) {
        this.patId = patId;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPatId() {
        return patId;
    }

    public void setPatId(String patientId) {
        this.patId = patientId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
