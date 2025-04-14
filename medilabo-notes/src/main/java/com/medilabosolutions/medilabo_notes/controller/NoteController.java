package com.medilabosolutions.medilabo_notes.controller;


import com.medilabosolutions.medilabo_notes.model.Note;
import com.medilabosolutions.medilabo_notes.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/notes")
public class NoteController {

    @Autowired
    private  NoteService noteService;

    //  Create a new Note
    @PostMapping
    public ResponseEntity<Note> createNote(@RequestBody Note newNote) {
        noteService.createNote(newNote);
        return ResponseEntity.ok(newNote);
    }

    //  Get all notes for a patient
    @GetMapping
    public ResponseEntity<List<Note>> getNotesByPatient(@RequestParam String patId) {
        List<Note> notes = noteService.getNotesByPatient(patId);
        return ResponseEntity.ok(notes);
    }

    @GetMapping("/{noteId}")
    public ResponseEntity<Note> getNoteByID(@PathVariable String noteId)
    {
        return ResponseEntity.ok(noteService.getNoteById(noteId));
    }

    //  Update a Note
    @PutMapping("/{noteId}")
    public ResponseEntity<Note> updateNote(@PathVariable String noteId, @RequestBody Note updatedNote) {
        Optional<Note> updated = noteService.updateNote(noteId, updatedNote);
        return updated.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    //  Delete a Note
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

