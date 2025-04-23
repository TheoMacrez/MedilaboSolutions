package com.medilabosolutions.medilabo_front.client;


import com.medilabosolutions.medilabo_front.config.OAuth2FeignRequestInterceptor;
import com.medilabosolutions.medilabo_front.dto.PatientDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Client Feign pour interagir avec le microservice des patients via la gateway.
 */
@FeignClient(name = "patient-client-gateway", url = "${patient.client.url}", configuration = OAuth2FeignRequestInterceptor.class)
public interface PatientClient {

    /**
     * Récupère la liste de tous les patients.
     */
    @GetMapping
    List<PatientDto> getAllPatients();

    /**
     * Récupère les informations d'un patient par son identifiant.
     */
    @GetMapping("/{id}")
    PatientDto getPatientById(@PathVariable("id") int id);

    /**
     * Crée un nouveau patient.
     */
    @PostMapping("/create")
    void createPatient(@RequestBody PatientDto patient);

    /**
     * Met à jour un patient existant.
     */
    @PutMapping("/{id}")
    void updatePatient(@PathVariable("id") int id, @RequestBody PatientDto patient);

    /**
     * Supprime un patient par son identifiant.
     */
    @DeleteMapping("/{id}")
    void deletePatient(@PathVariable("id") int id);
}


