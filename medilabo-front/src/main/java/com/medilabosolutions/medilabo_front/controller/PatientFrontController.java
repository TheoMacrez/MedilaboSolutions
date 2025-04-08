package com.medilabosolutions.medilabo_front.controller;

import com.medilabosolutions.medilabo_diabetes_assessment.model.AssessmentRisk;
import com.medilabosolutions.medilabo_front.client.AssessmentClient;
import com.medilabosolutions.medilabo_front.client.NoteClient;
import com.medilabosolutions.medilabo_front.client.PatientClient;
import com.medilabosolutions.medilabo_front.dto.NoteDto;
import com.medilabosolutions.medilabo_front.dto.PatientDto;
import com.medilabosolutions.medilabo_patient.model.Patient;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/patients")
public class PatientFrontController {

    private final PatientClient patientClient;

    private final NoteClient noteClient;

    private final AssessmentClient assessmentClient;

    @GetMapping
    public String listPatients(Model model) {
        List<PatientDto> patients = patientClient.getAllPatients();

        model.addAttribute("patients", patients);

        return "patients";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("patient", new PatientDto());
        return "addForm";
    }

    @PostMapping("/add")
    public String addPatient(@Valid @ModelAttribute("patient") PatientDto patientDto,
                             BindingResult result) {
        if (result.hasErrors()) {
            return "addForm";
        }
        patientClient.createPatient(patientDto);
        return "redirect:/patients";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        PatientDto patient = patientClient.getPatientById(id);
        model.addAttribute("patient", patient);
        return "updateForm";
    }

    @PostMapping("/edit/{id}")
    public String updatePatient(@PathVariable int id,
                                @Valid @ModelAttribute("patient") PatientDto patientDto,
                                BindingResult result) {
        if (result.hasErrors()) {
            return "updateForm";
        }
        patientClient.updatePatient(id, patientDto);
        return "redirect:/patients";
    }

    @GetMapping("/delete/{id}")
    public String deletePatient(@PathVariable int id) {
        patientClient.deletePatient(id);
        return "redirect:/patients";
    }

    @GetMapping("/profile/{id}")
    public String showPatientProfile(@PathVariable int id, Model model) {
        PatientDto patient = patientClient.getPatientById(id);
        List<NoteDto> notes = noteClient.getNotesByPatientId(String.valueOf(id));

        // Ajout de l'assessment pour ce patient
        String assessment = "Indisponible";
        AssessmentRisk risk = assessmentClient.assessPatient(id);
        assessment = risk.name();

        model.addAttribute("patient", patient);
        model.addAttribute("notes", notes);
        model.addAttribute("assessment", assessment);

        return "profile"; // Le nom du fichier HTML à afficher
    }

}

