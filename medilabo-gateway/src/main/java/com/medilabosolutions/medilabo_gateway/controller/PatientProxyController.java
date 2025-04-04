package com.medilabosolutions.medilabo_gateway.controller;

import com.medilabosolutions.medilabo_gateway.client.PatientClient;
import com.medilabosolutions.medilabo_gateway.dto.PatientDto;
import com.medilabosolutions.medilabo_gateway.mapper.PatientMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientProxyController {

    private final PatientClient patientClient;
    private final PatientMapper patientMapper;

    public PatientProxyController(PatientClient patientClient, PatientMapper patientMapper) {
        this.patientClient = patientClient;
        this.patientMapper = patientMapper;
    }

    @GetMapping
    public ResponseEntity<List<PatientDto>> getAllPatients() {
        List<PatientDto> patients = patientClient.getAllPatients();
        return ResponseEntity.ok(patients);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientDto> getPatientById(@PathVariable int id) {
        PatientDto patient = patientClient.getPatientById(id);
        return ResponseEntity.ok(patient);
    }

    @GetMapping("/search")
    public ResponseEntity<PatientDto> getPatientByName(
            @RequestParam String firstName,
            @RequestParam String lastName) {
        PatientDto patient = patientClient.getPatientByName(firstName, lastName);
        return ResponseEntity.ok(patient);
    }

    @PostMapping("/create")
    public ResponseEntity<PatientDto> createPatient(@RequestBody PatientDto patientDto) {
        PatientDto createdPatient = patientClient.createPatient(patientDto);
        return new ResponseEntity<>(createdPatient, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientDto> updatePatient(@PathVariable int id, @RequestBody PatientDto patientDto) {
        PatientDto updatedPatient = patientClient.updatePatient(id, patientDto);
        return ResponseEntity.ok(updatedPatient);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable int id) {
        // Retourne une réponse vide avec un code de statut approprié (No Content)
        patientClient.deletePatient(id);
        return ResponseEntity.noContent().build();
    }
}
