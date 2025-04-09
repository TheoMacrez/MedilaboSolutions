package com.medilabosolutions.medilabo_front.controller;

import com.medilabosolutions.medilabo_front.client.NoteClient;
import com.medilabosolutions.medilabo_front.client.PatientClient;
import com.medilabosolutions.medilabo_front.dto.NoteDto;
import com.medilabosolutions.medilabo_front.dto.PatientDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/notes")
public class NoteFrontController {

    private final PatientClient patientClient;
    private final NoteClient noteClient;

    @GetMapping("/create")
    public String showAddNoteForm(@RequestParam("patId") String patId, Model model) {
        PatientDto patient = patientClient.getPatientById(Integer.parseInt(patId));
        NoteDto note = new NoteDto();
        note.setPatId(patId);
        model.addAttribute("note", note);
        return "addNoteForm";
    }

    @PostMapping("/create")
    public String addNote(@ModelAttribute("note") NoteDto note) {
        noteClient.createNote(note);
        return "redirect:/patients"  + "/profile/"+ note.getPatId();
    }

    @GetMapping("/edit")
    public String showUpdateNoteForm(@RequestParam("noteId") String noteId, Model model) {
        NoteDto note = noteClient.getNoteById(noteId);
        model.addAttribute("note", note);
        return "updateNoteForm";
    }

    @PostMapping("/edit")
    public String updateNote(@ModelAttribute("note") NoteDto note) {
        noteClient.updateNote(note.getId(),note);
        return "redirect:/patients"  + "/profile/"+ note.getPatId();
    }

    @GetMapping("/delete")
    public String deleteNote(@RequestParam("noteId") String noteId, @RequestParam("patId") String patId) {
        noteClient.deleteNote(noteId);
        return "redirect:/patients"  + "/profile/"+ patId;
    }

}
