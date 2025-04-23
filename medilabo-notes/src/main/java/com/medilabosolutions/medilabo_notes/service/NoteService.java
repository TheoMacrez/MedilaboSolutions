package com.medilabosolutions.medilabo_notes.service;

import com.medilabosolutions.medilabo_notes.model.Note;
import com.medilabosolutions.medilabo_notes.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * Service permettant de gérer les opérations métier liées aux Notes.
 */
@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    /**
     * Récupère une note à partir de son identifiant.
     *
     * @param noteId l'identifiant de la note
     * @return la note si elle est trouvée, sinon null
     */
    public Note getNoteById(String noteId) {
        Optional<Note> optionalNote = noteRepository.findById(noteId);
        return optionalNote.orElse(null);
    }

    /**
     * Crée une nouvelle note dans la base de données.
     *
     * @param note la note à créer
     */
    public void createNote(Note note) {
        noteRepository.insert(note);
    }

    /**
     * Récupère toutes les notes associées à un patient.
     *
     * @param patientId l'identifiant du patient
     * @return la liste des notes du patient
     */
    public List<Note> getNotesByPatient(String patientId) {
        return noteRepository.findByPatId(patientId);
    }

    /**
     * Met à jour une note existante.
     *
     * @param noteId l'identifiant de la note à mettre à jour
     * @param newNote la nouvelle version de la note
     * @return un Optional contenant la note mise à jour si elle existe, sinon un Optional vide
     */
    public Optional<Note> updateNote(String noteId, Note newNote) {
        Optional<Note> optionalNote = noteRepository.findById(noteId);

        if (optionalNote.isPresent()) {
            Note note = optionalNote.get();
            note.setPatId(newNote.getPatId());
            note.setContent(newNote.getContent());
            return Optional.of(noteRepository.save(note));
        }
        return Optional.empty();
    }

    /**
     * Supprime une note par son identifiant.
     *
     * @param noteId l'identifiant de la note à supprimer
     * @return true si la note a été supprimée, false sinon
     */
    public boolean deleteNote(String noteId) {
        if (noteRepository.existsById(noteId)) {
            noteRepository.deleteById(noteId);
            return true;
        }
        return false;
    }
}
