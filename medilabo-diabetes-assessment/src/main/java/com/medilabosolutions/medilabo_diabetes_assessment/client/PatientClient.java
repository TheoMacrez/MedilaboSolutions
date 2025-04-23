package com.medilabosolutions.medilabo_diabetes_assessment.client;

import com.medilabosolutions.medilabo_diabetes_assessment.config.FeignClientTokenInterceptor;
import com.medilabosolutions.medilabo_diabetes_assessment.dto.PatientDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "patient-service", url = "${patient.service.url}",configuration = FeignClientTokenInterceptor.class)
public interface PatientClient {

    @GetMapping("/{id}")
    PatientDto getPatientById(@PathVariable("id") int id);
}
