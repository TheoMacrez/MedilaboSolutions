package com.medilabosolutions.medilabo_front.client;

import com.medilabosolutions.medilabo_diabetes_assessment.model.AssessmentRisk;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "assessment-client-gateway", url = "${assessment.client.url}")
public interface AssessmentClient {

    @GetMapping("/{patId}")
    public AssessmentRisk assessPatient(@PathVariable int patId);

}
