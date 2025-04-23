package com.medilabosolutions.medilabo_front.controller;

import com.medilabosolutions.medilabo_front.client.NoteClient;
import com.medilabosolutions.medilabo_front.client.PatientClient;
import com.medilabosolutions.medilabo_front.dto.NoteDto;
import com.medilabosolutions.medilabo_front.dto.PatientDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Controller permettant de gérer les opérations liées aux notes de santé via l'interface front-end.
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/notes")
public class NoteFrontController {

    private final PatientClient patientClient;
    private final NoteClient noteClient;

    /**
     * Affiche le formulaire de création d'une nouvelle note.
     */
    @GetMapping("/create")
    public String showAddNoteForm(@RequestParam("patId") String patId, Model model) {
        PatientDto patient = patientClient.getPatientById(Integer.parseInt(patId));
        NoteDto note = new NoteDto();
        note.setPatId(patId);
        model.addAttribute("note", note);
        return "addNoteForm";
    }

    /**
     * Traite la création d'une nouvelle note.
     */
    @PostMapping("/create")
    public String addNote(@ModelAttribute("note") NoteDto note) {
        noteClient.createNote(note);
        return "redirect:/patients"  + "/profile/"+ note.getPatId();
    }

    /**
     * Affiche le formulaire de mise à jour d'une note existante.
     */
    @GetMapping("/edit")
    public String showUpdateNoteForm(@RequestParam("noteId") String noteId, Model model) {
        NoteDto note = noteClient.getNoteById(noteId);
        model.addAttribute("note", note);
        return "updateNoteForm";
    }

    /**
     * Traite la mise à jour d'une note existante.
     */
    @PostMapping("/edit")
    public String updateNote(@ModelAttribute("note") NoteDto note) {
        noteClient.updateNote(note.getId(),note);
        return "redirect:/patients"  + "/profile/"+ note.getPatId();
    }

    /**
     * Supprime une note selon son identifiant.
     */
    @GetMapping("/delete")
    public String deleteNote(@RequestParam("noteId") String noteId, @RequestParam("patId") String patId) {
        noteClient.deleteNote(noteId);
        return "redirect:/patients"  + "/profile/"+ patId;
    }

}
