package com.medilabosolutions.medilabo_front.client;

import com.medilabosolutions.medilabo_front.dto.NoteDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "note-client-gateway", url = "${note.client.url}")
public interface NoteClient {

    @GetMapping
    List<NoteDto> getNotesByPatientId(@RequestParam("patId") String patId);

    @PostMapping
    void createNote(@RequestBody NoteDto note);

    @GetMapping("/{noteId}")
    NoteDto getNoteById(@PathVariable("noteId") String noteId);

    @PutMapping("/{noteId}")
    void updateNote(@PathVariable("noteId") String noteId, @RequestBody NoteDto noteDto);

    @DeleteMapping("/{noteId}")
    void deleteNote(@PathVariable("noteId") String noteId);
}
