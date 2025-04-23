package com.medilabosolutions.medilabo_front.client;

import com.medilabosolutions.medilabo_front.config.OAuth2FeignRequestInterceptor;
import com.medilabosolutions.medilabo_front.dto.NoteDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Client Feign pour interagir avec le microservice des notes via la gateway.
 */
@FeignClient(name = "note-client-gateway", url = "${note.client.url}", configuration = OAuth2FeignRequestInterceptor.class)
public interface NoteClient {

    /**
     * Récupère toutes les notes associées à un patient donné.
     */
    @GetMapping
    List<NoteDto> getNotesByPatientId(@RequestParam("patId") String patId);

    /**
     * Crée une nouvelle note pour un patient.
     */
    @PostMapping
    void createNote(@RequestBody NoteDto note);

    /**
     * Récupère une note par son identifiant.
     */
    @GetMapping("/{noteId}")
    NoteDto getNoteById(@PathVariable("noteId") String noteId);

    /**
     * Met à jour une note existante.
     */
    @PutMapping("/{noteId}")
    void updateNote(@PathVariable("noteId") String noteId, @RequestBody NoteDto noteDto);

    /**
     * Supprime une note par son identifiant.
     */
    @DeleteMapping("/{noteId}")
    void deleteNote(@PathVariable("noteId") String noteId);
}

