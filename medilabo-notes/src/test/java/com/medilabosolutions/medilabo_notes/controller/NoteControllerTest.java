package com.medilabosolutions.medilabo_notes.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.medilabosolutions.medilabo_notes.model.Note;
import com.medilabosolutions.medilabo_notes.repository.NoteRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class NoteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private ObjectMapper objectMapper; // pour convertir des objets en JSON

    private Note note;

    @BeforeEach
    void setUp() {
        noteRepository.deleteAll(); // Nettoyer la base avant chaque test

        note = new Note("patient123", "Première note de test");
        note = noteRepository.save(note); // On sauvegarde un note pour tester dessus
    }

    @AfterEach
    void deleteAfterTest()
    {
        noteRepository.deleteAll(); // Nettoyer la base après chaque test
    }

    @Test
    void testCreateNote() throws Exception {
        Note newNote = new Note("patient456", "Nouvelle note");

        mockMvc.perform(post("/notes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newNote)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.patId").value("patient456"))
                .andExpect(jsonPath("$.content").value("Nouvelle note"));
    }

    @Test
    void testGetNotesByPatient() throws Exception {
        mockMvc.perform(get("/notes")
                        .param("patId", "patient123"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].patId").value("patient123"))
                .andExpect(jsonPath("$[0].content").value("Première note de test"));
    }

    @Test
    void testGetNoteById() throws Exception {
        mockMvc.perform(get("/notes/" + note.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.patId").value("patient123"))
                .andExpect(jsonPath("$.content").value("Première note de test"));
    }

    @Test
    void testUpdateNote() throws Exception {
        Note updatedNote = new Note("patient123", "Note mise à jour");

        mockMvc.perform(put("/notes/" + note.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedNote)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").value("Note mise à jour"));
    }

    @Test
    void testDeleteNote() throws Exception {
        mockMvc.perform(delete("/notes/" + note.getId()))
                .andExpect(status().isNoContent());
    }

    @Test
    void testDeleteNonExistingNote() throws Exception {
        mockMvc.perform(delete("/notes/invalid-id"))
                .andExpect(status().isNotFound());
    }
}
