package com.medilabosolutions.medilabo_front.controller;

import com.medilabosolutions.medilabo_front.client.PatientClient;
import com.medilabosolutions.medilabo_front.dto.PatientDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/patients")
public class PatientFrontController {

    private final PatientClient patientClient;

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
}

