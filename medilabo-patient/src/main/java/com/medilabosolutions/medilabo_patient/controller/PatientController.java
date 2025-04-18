package com.medilabosolutions.medilabo_patient.controller;

import com.medilabosolutions.medilabo_patient.model.Patient;
import com.medilabosolutions.medilabo_patient.service.PatientService;
import com.medilabosolutions.medilabo_patient.exceptions.PatientNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    @Autowired
    private  PatientService patientService;

    @GetMapping
    public ResponseEntity<List<Patient>> getAllPatient() {
        return ResponseEntity.ok(patientService.getAllPatients());
    }

    // Ajouter un patient
    @PostMapping("/create")
    public ResponseEntity<Patient> createPatient(@Valid @RequestBody Patient patient) {
        return ResponseEntity.ok(patientService.createPatient(patient));
    }

    // Récupérer un patient par ID
    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable int id) {
        Patient patient = patientService.getPatientById(id);
        return ResponseEntity.ok(patient);
    }

    // Récupérer un patient par nom et prénom
    @GetMapping("/search")
    public ResponseEntity<Patient> getPatientByName(
            @RequestParam String firstName, @RequestParam String lastName) {
        Patient patient = patientService.getPatientByName(firstName, lastName);
        return ResponseEntity.ok(patient);
    }

    // Mettre à jour un patient
    @PutMapping("/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable int id, @Valid @RequestBody Patient patientDetails) {
        Patient updatedPatient = patientService.updatePatient(id, patientDetails);
        return ResponseEntity.ok(updatedPatient);
    }

    // Supprimer un patient
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable int id) {
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }
}
