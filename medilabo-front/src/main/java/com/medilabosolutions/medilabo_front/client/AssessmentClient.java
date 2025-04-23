package com.medilabosolutions.medilabo_front.client;


import com.medilabosolutions.medilabo_front.config.OAuth2FeignRequestInterceptor;
import com.medilabosolutions.medilabo_front.dto.AssessmentRiskDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "assessment-client-gateway", url = "${assessment.client.url}", configuration = OAuth2FeignRequestInterceptor.class)
public interface AssessmentClient {

    @GetMapping("/{patId}")
    public AssessmentRiskDto assessPatient(@PathVariable int patId);

}
