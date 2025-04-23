package com.medilabosolutions.medilabo_notes.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.medilabosolutions.medilabo_notes.config.TestSecurityConfig;
import com.medilabosolutions.medilabo_notes.model.Note;
import com.medilabosolutions.medilabo_notes.service.NoteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(NoteController.class)
@Import(TestSecurityConfig.class)
class NoteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NoteService noteService; // On MOCKE le service, pas le repository

    @Autowired
    private ObjectMapper objectMapper;

    private Note note;

    @BeforeEach
    void setUp() {
        note = new Note("patient123", "Première note de test");
        note.setId("noteId123");
    }

    @Test
    void testCreateNote() throws Exception {
        Note newNote = new Note("patient456", "Nouvelle note");

        doNothing().when(noteService).createNote(any(Note.class));

        mockMvc.perform(post("/api/notes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newNote)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.patId").value("patient456"))
                .andExpect(jsonPath("$.content").value("Nouvelle note"));
        verify(noteService, times(1)).createNote(any(Note.class));
    }

    @Test
    void testGetNotesByPatient() throws Exception {
        when(noteService.getNotesByPatient("patient123")).thenReturn(List.of(note));

        mockMvc.perform(get("/api/notes")
                        .param("patId", "patient123"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].patId").value("patient123"))
                .andExpect(jsonPath("$[0].content").value("Première note de test"));
    }

    @Test
    void testGetNoteById() throws Exception {
        when(noteService.getNoteById("noteId123")).thenReturn(note);

        mockMvc.perform(get("/api/notes/noteId123"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.patId").value("patient123"))
                .andExpect(jsonPath("$.content").value("Première note de test"));
    }

    @Test
    void testUpdateNote() throws Exception {
        Note updatedNote = new Note("patient123", "Note mise à jour");
        when(noteService.updateNote(eq("noteId123"), any(Note.class)))
                .thenReturn(Optional.of(updatedNote));

        mockMvc.perform(put("/api/notes/noteId123")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedNote)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").value("Note mise à jour"));
    }

    @Test
    void testDeleteNote() throws Exception {
        when(noteService.deleteNote("noteId123")).thenReturn(true);

        mockMvc.perform(delete("/api/notes/noteId123"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testDeleteNonExistingNote() throws Exception {
        when(noteService.deleteNote("invalid-id")).thenReturn(false);

        mockMvc.perform(delete("/api/notes/invalid-id"))
                .andExpect(status().isNotFound());
    }
}
