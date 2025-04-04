package com.medilabosolutions.medilabo_gateway.client;

import com.medilabosolutions.medilabo_gateway.config.FeignConfig;
import com.medilabosolutions.medilabo_gateway.dto.PatientDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "medilabo-patient", url = "http://localhost:8081", configuration = FeignConfig.class) // adapte l’URL ou nom Eureka
public interface PatientClient {

    @GetMapping("/patients")
    List<PatientDto> getAllPatients();

    @PostMapping("/patients/create")
    PatientDto createPatient(@RequestBody PatientDto patient);

    @GetMapping("/patients/{id}")
    PatientDto getPatientById(@PathVariable("id") int id);

    @GetMapping("/patients/search")
    PatientDto getPatientByName(
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName
    );

    @PutMapping("/patients/{id}")
    PatientDto updatePatient(
            @PathVariable("id") int id,
            @RequestBody PatientDto patientDetails
    );

    @DeleteMapping("/patients/{id}")
    Void deletePatient(@PathVariable("id") int id);
}
