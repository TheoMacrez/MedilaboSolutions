package com.medilabosolutions.medilabo_notes.service;

import com.medilabosolutions.medilabo_notes.model.Note;
import com.medilabosolutions.medilabo_notes.repository.NoteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class NoteServiceTest {

    @Mock
    private NoteRepository noteRepository;

    @InjectMocks
    private NoteService noteService;

    private Note note;

    @BeforeEach
    void setUp() {
        note = new Note("patient1", "Patient has a high blood pressure.");
        note.setId("note123");
    }

    @Test
    void getNoteById_existingId_returnsNote() {
        // Arrange
        when(noteRepository.findById("note123")).thenReturn(Optional.of(note));

        // Act
        Note result = noteService.getNoteById("note123");

        // Assert
        assertNotNull(result);
        assertEquals("patient1", result.getPatId());
        verify(noteRepository).findById("note123");
    }

    @Test
    void getNoteById_nonExistingId_returnsNull() {
        // Arrange
        when(noteRepository.findById("note456")).thenReturn(Optional.empty());

        // Act
        Note result = noteService.getNoteById("note456");

        // Assert
        assertNull(result);
        verify(noteRepository).findById("note456");
    }

    @Test
    void createNote_success() {
        // Act
        noteService.createNote(note);

        // Assert
        verify(noteRepository).insert(note);
    }

    @Test
    void getNotesByPatient_existingPatientId_returnsNotes() {
        // Arrange
        List<Note> notes = Arrays.asList(
                new Note("patient1", "Note 1"),
                new Note("patient1", "Note 2")
        );
        when(noteRepository.findByPatId("patient1")).thenReturn(notes);

        // Act
        List<Note> result = noteService.getNotesByPatient("patient1");

        // Assert
        assertEquals(2, result.size());
        verify(noteRepository).findByPatId("patient1");
    }

    @Test
    void updateNote_existingId_returnsUpdatedNote() {
        // Arrange
        Note newNote = new Note("patient2", "Updated content.");
        when(noteRepository.findById("note123")).thenReturn(Optional.of(note));
        when(noteRepository.save(any(Note.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        Optional<Note> updatedNote = noteService.updateNote("note123", newNote);

        // Assert
        assertTrue(updatedNote.isPresent());
        assertEquals("patient2", updatedNote.get().getPatId());
        assertEquals("Updated content.", updatedNote.get().getContent());
        verify(noteRepository).findById("note123");
        verify(noteRepository).save(any(Note.class));
    }

    @Test
    void updateNote_nonExistingId_returnsEmptyOptional() {
        // Arrange
        Note newNote = new Note("patient2", "Updated content.");
        when(noteRepository.findById("note456")).thenReturn(Optional.empty());

        // Act
        Optional<Note> updatedNote = noteService.updateNote("note456", newNote);

        // Assert
        assertFalse(updatedNote.isPresent());
        verify(noteRepository).findById("note456");
        verify(noteRepository, never()).save(any());
    }

    @Test
    void deleteNote_existingId_returnsTrue() {
        // Arrange
        when(noteRepository.existsById("note123")).thenReturn(true);

        // Act
        boolean result = noteService.deleteNote("note123");

        // Assert
        assertTrue(result);
        verify(noteRepository).existsById("note123");
        verify(noteRepository).deleteById("note123");
    }

    @Test
    void deleteNote_nonExistingId_returnsFalse() {
        // Arrange
        when(noteRepository.existsById("note456")).thenReturn(false);

        // Act
        boolean result = noteService.deleteNote("note456");

        // Assert
        assertFalse(result);
        verify(noteRepository).existsById("note456");
        verify(noteRepository, never()).deleteById(anyString());
    }
}
