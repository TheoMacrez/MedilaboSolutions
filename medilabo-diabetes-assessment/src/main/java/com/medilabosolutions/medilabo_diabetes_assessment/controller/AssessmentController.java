package com.medilabosolutions.medilabo_diabetes_assessment.controller;

import com.medilabosolutions.medilabo_diabetes_assessment.model.AssessmentRisk;
import com.medilabosolutions.medilabo_diabetes_assessment.service.AssessmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Contrôleur exposant une API REST pour évaluer le risque de diabète d'un patient.
 */
@RestController
@RequestMapping("/assessment")
public class AssessmentController {

    @Autowired
    private AssessmentService assessmentService;

    /**
     * Évalue le risque de diabète pour un patient donné.
     *
     * @param patId identifiant du patient
     * @return niveau de risque {@link AssessmentRisk}
     */
    @GetMapping("/{patId}")
    public AssessmentRisk assessPatient(@PathVariable int patId) {
        return assessmentService.assessRisk(patId);
    }
}

