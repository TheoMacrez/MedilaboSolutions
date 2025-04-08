package com.medilabosolutions.medilabo_diabetes_assessment.controller;

import com.medilabosolutions.medilabo_diabetes_assessment.model.AssessmentRisk;
import com.medilabosolutions.medilabo_diabetes_assessment.service.AssessmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/assessment")
public class AssessmentController {

    @Autowired
    private  AssessmentService assessmentService;


    @GetMapping("/{patId}")
    public AssessmentRisk assessPatient(@PathVariable int patId) {
        return assessmentService.assessRisk(patId);
    }
}

