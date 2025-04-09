package com.medilabosolutions.medilabo_diabetes_assessment.controller;

import com.medilabosolutions.medilabo_diabetes_assessment.model.AssessmentRisk;
import com.medilabosolutions.medilabo_diabetes_assessment.service.AssessmentService;
import com.medilabosolutions.medilabo_patient.exceptions.PatientNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(AssessmentController.class)
class AssessmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AssessmentService assessmentService;

    @Test
    void assessPatient_ShouldReturnAssessmentRisk() throws Exception {
        // Given
        int patientId = 1;
        AssessmentRisk expectedRisk = AssessmentRisk.InDanger;

        when(assessmentService.assessRisk(patientId)).thenReturn(expectedRisk);

        // When & Then
        mockMvc.perform(get("/assessment/{patId}", patientId))
                .andExpect(status().isOk())
                .andExpect(content().json("\"InDanger\""));
    }

    @Test
    @DisplayName("Should return 404 when patient is not found")
    void assessPatient_patientNotFound() throws Exception {
        // Arrange : simuler que assessRisk() lève une PatientNotFoundException
        Mockito.when(assessmentService.assessRisk(anyInt()))
                .thenThrow(new PatientNotFoundException("Patient not found"));

        // Act + Assert
        mockMvc.perform(get("/assessment/1") // exemple avec id 1
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
