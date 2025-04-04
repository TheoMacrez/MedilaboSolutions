package com.medilabosolutions.medilabo_front.client;

import com.medilabosolutions.medilabo_gateway.dto.PatientDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "medilabo-gateway", url = "http://localhost:8080/api/patients")
public interface PatientClient {

    @GetMapping
    List<PatientDto> getAllPatients();

    @GetMapping("/{id}")
    PatientDto getPatientById(@PathVariable("id") int id);

    @PostMapping("/create")
    PatientDto createPatient(@RequestBody PatientDto patient);

    @PutMapping("/{id}")
    PatientDto updatePatient(@PathVariable("id") int id, @RequestBody PatientDto patient);

    @DeleteMapping("/{id}")
    void deletePatient(@PathVariable("id") int id);
}

