package com.medilabosolutions.medilabo_front.client;

import com.medilabosolutions.medilabo_front.dto.PatientDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "patient-client-gateway", url = "${patient.client.url}")
public interface PatientClient {

    @GetMapping
    List<PatientDto> getAllPatients();

    @GetMapping("/{id}")
    PatientDto getPatientById(@PathVariable("id") int id);

    @PostMapping("/create")
    void createPatient(@RequestBody PatientDto patient);

    @PutMapping("/{id}")
    void updatePatient(@PathVariable("id") int id, @RequestBody PatientDto patient);

    @DeleteMapping("/{id}")
    void deletePatient(@PathVariable("id") int id);
}

