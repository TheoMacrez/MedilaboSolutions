package com.medilabosolutions.medilabo_patient.controller;

import com.medilabosolutions.medilabo_patient.model.Patient;
import com.medilabosolutions.medilabo_patient.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Contrôleur REST pour gérer les patients.
 */
@RestController
@RequestMapping("/api/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    /**
     * Récupère la liste de tous les patients.
     *
     * @return La liste des patients.
     */
    @GetMapping
    public ResponseEntity<List<Patient>> getAllPatient() {
        return ResponseEntity.ok(patientService.getAllPatients());
    }

    /**
     * Crée un nouveau patient.
     *
     * @param patient Les informations du patient à créer.
     * @return Le patient créé.
     */
    @PostMapping("/create")
    public ResponseEntity<Patient> createPatient(@Valid @RequestBody Patient patient) {
        return ResponseEntity.ok(patientService.createPatient(patient));
    }

    /**
     * Récupère un patient par son identifiant.
     *
     * @param id L'identifiant du patient.
     * @return Le patient correspondant.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable int id) {
        Patient patient = patientService.getPatientById(id);
        return ResponseEntity.ok(patient);
    }

    /**
     * Récupère un patient par son prénom et son nom.
     *
     * @param firstName Le prénom du patient.
     * @param lastName  Le nom du patient.
     * @return Le patient correspondant.
     */
    @GetMapping("/search")
    public ResponseEntity<Patient> getPatientByName(
            @RequestParam String firstName, @RequestParam String lastName) {
        Patient patient = patientService.getPatientByName(firstName, lastName);
        return ResponseEntity.ok(patient);
    }

    /**
     * Met à jour les informations d'un patient existant.
     *
     * @param id              L'identifiant du patient à mettre à jour.
     * @param patientDetails  Les nouvelles informations du patient.
     * @return Le patient mis à jour.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable int id, @Valid @RequestBody Patient patientDetails) {
        Patient updatedPatient = patientService.updatePatient(id, patientDetails);
        return ResponseEntity.ok(updatedPatient);
    }

    /**
     * Supprime un patient par son identifiant.
     *
     * @param id L'identifiant du patient à supprimer.
     * @return Une réponse vide.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable int id) {
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }
}
