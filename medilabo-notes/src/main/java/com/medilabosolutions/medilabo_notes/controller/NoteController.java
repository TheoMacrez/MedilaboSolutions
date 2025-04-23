package com.medilabosolutions.medilabo_notes.controller;

import com.medilabosolutions.medilabo_notes.model.Note;
import com.medilabosolutions.medilabo_notes.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Contrôleur REST pour la gestion des notes médicales.
 */
@RestController
@RequestMapping("/api/notes")
public class NoteController {

    @Autowired
    private NoteService noteService;

    /**
     * Crée une nouvelle note.
     *
     * @param newNote la note à créer
     * @return la note créée
     */
    @PostMapping
    public ResponseEntity<Note> createNote(@RequestBody Note newNote) {
        noteService.createNote(newNote);
        return ResponseEntity.ok(newNote);
    }

    /**
     * Récupère toutes les notes associées à un patient.
     *
     * @param patId l'identifiant du patient
     * @return liste des notes
     */
    @GetMapping
    public ResponseEntity<List<Note>> getNotesByPatient(@RequestParam String patId) {
        List<Note> notes = noteService.getNotesByPatient(patId);
        return ResponseEntity.ok(notes);
    }

    /**
     * Récupère une note par son identifiant.
     *
     * @param noteId l'identifiant de la note
     * @return la note correspondante
     */
    @GetMapping("/{noteId}")
    public ResponseEntity<Note> getNoteByID(@PathVariable String noteId) {
        return ResponseEntity.ok(noteService.getNoteById(noteId));
    }

    /**
     * Met à jour une note existante.
     *
     * @param noteId l'identifiant de la note
     * @param updatedNote les nouvelles informations de la note
     * @return la note mise à jour si elle existe, sinon une réponse 404
     */
    @PutMapping("/{noteId}")
    public ResponseEntity<Note> updateNote(@PathVariable String noteId, @RequestBody Note updatedNote) {
        Optional<Note> updated = noteService.updateNote(noteId, updatedNote);
        return updated.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Supprime une note par son identifiant.
     *
     * @param noteId l'identifiant de la note
     * @return une réponse 204 No Content si la suppression a réussi, sinon 404 Not Found
     */
    @DeleteMapping("/{noteId}")
    public ResponseEntity<Void> deleteNote(@PathVariable String noteId) {
        boolean deleted = noteService.deleteNote(noteId);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
