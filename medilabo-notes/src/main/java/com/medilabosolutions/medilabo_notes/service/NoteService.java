package com.medilabosolutions.medilabo_notes.service;
import com.medilabosolutions.medilabo_notes.model.Note;
import com.medilabosolutions.medilabo_notes.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    public Note getNoteById(String noteId)
    {
        Optional<Note> optionalNote = noteRepository.findById(noteId);
        return optionalNote.orElse(null);

    }

    public void createNote(Note note) {
        noteRepository.insert(note);
    }

    public List<Note> getNotesByPatient(String patientId) {
        return noteRepository.findByPatId(patientId);
    }

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

    public boolean deleteNote(String noteId) {
        if (noteRepository.existsById(noteId)) {
            noteRepository.deleteById(noteId);
            return true;
        }
        return false;
    }
}

