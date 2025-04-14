package com.medilabosolutions.medilabo_diabetes_assessment.service;

import com.medilabosolutions.medilabo_diabetes_assessment.client.NoteClient;
import com.medilabosolutions.medilabo_diabetes_assessment.client.PatientClient;
import com.medilabosolutions.medilabo_diabetes_assessment.dto.Gender;
import com.medilabosolutions.medilabo_diabetes_assessment.dto.NoteDto;
import com.medilabosolutions.medilabo_diabetes_assessment.dto.PatientDto;
import com.medilabosolutions.medilabo_diabetes_assessment.model.AssessmentRisk;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AssessmentServiceTest {

    @Mock
    private PatientClient patientClient;
    @Mock
    private NoteClient noteClient;
    @InjectMocks
    private AssessmentService assessmentService;

    @BeforeEach
    void setUp() {
        assessmentService = new AssessmentService(patientClient, noteClient);
    }

    @Test
    void assessRisk_shouldReturnNone_whenNoTriggerWords() {
        // Arrange
        PatientDto patient = new PatientDto(1, "John", "Doe", LocalDate.of(1980, 1, 1), Gender.M);
        NoteDto note = new NoteDto();
        note.setContent("Pas de problème de santé.");

        when(patientClient.getPatientById(1)).thenReturn(patient);
        when(noteClient.getNotesByPatientId("1")).thenReturn(List.of(note));

        // Act
        AssessmentRisk risk = assessmentService.assessRisk(1);

        // Assert
        assertEquals(AssessmentRisk.None, risk);
    }

    @Test
    void assessRisk_shouldReturnBorderline_when2To5TriggerWordsAndAgeAbove30() {
        // Arrange
        PatientDto patient = new PatientDto(1, "Jane", "Smith", LocalDate.of(1980, 1, 1), Gender.F);
        NoteDto note = new NoteDto();
        note.setContent("La patiente à de l'Hémoglobine A1C et des problèmes de Cholestérol.");

        when(patientClient.getPatientById(1)).thenReturn(patient);
        when(noteClient.getNotesByPatientId("1")).thenReturn(List.of(note));

        // Act
        AssessmentRisk risk = assessmentService.assessRisk(1);

        // Assert
        assertEquals(AssessmentRisk.Borderline, risk);
    }

    @Test
    void assessRisk_shouldReturnInDanger_forMaleUnder30_with3TriggerWords() {
        // Arrange
        PatientDto patient = new PatientDto(1, "Mike", "Young", LocalDate.now().minusYears(25), Gender.M);
        NoteDto note = new NoteDto();
        note.setContent("Le patient est fumeur, a de l'Hémoglobine A1C, et des problèmes de Cholestérol.");

        when(patientClient.getPatientById(1)).thenReturn(patient);
        when(noteClient.getNotesByPatientId("1")).thenReturn(List.of(note));

        // Act
        AssessmentRisk risk = assessmentService.assessRisk(1);

        // Assert
        assertEquals(AssessmentRisk.InDanger, risk);
    }

    @Test
    void assessRisk_shouldReturnEarlyOnset_forFemaleUnder30_with7TriggerWords() {
        // Arrange
        PatientDto patient = new PatientDto(1, "Emily", "Young", LocalDate.now().minusYears(25), Gender.F);
        NoteDto note = new NoteDto();
        note.setContent("La patiente est fumeuse, a de l'Hémoglobine A1C, des problèmes de Cholestérol, une grande taille, un poids anormal, peu d'anticorps et des vertiges.");

        when(patientClient.getPatientById(1)).thenReturn(patient);
        when(noteClient.getNotesByPatientId("1")).thenReturn(List.of(note));

        // Act
        AssessmentRisk risk = assessmentService.assessRisk(1);

        // Assert
        assertEquals(AssessmentRisk.EarlyOnset, risk);
    }

    @Test
    void assessRisk_shouldReturnNone_whenNoNotes() {
        // Arrange
        PatientDto patient = new PatientDto(1, "Lucas", "Brown", LocalDate.of(1990, 1, 1), Gender.Other);

        when(patientClient.getPatientById(1)).thenReturn(patient);
        when(noteClient.getNotesByPatientId("1")).thenReturn(Collections.emptyList());

        // Act
        AssessmentRisk risk = assessmentService.assessRisk(1);

        // Assert
        assertEquals(AssessmentRisk.None, risk);
    }

    @Test
    void assessRisk_shouldReturnEarlyOnset_forOtherGenderUnder30_with7TriggerWords() {
        // Arrange
        PatientDto patient = new PatientDto(1, "Alex", "Jordan", LocalDate.now().minusYears(25), Gender.Other);
        NoteDto note = new NoteDto();
        note.setContent("Le.la patient.e est fumeur.euse, a de l'Hémoglobine A1C, des problèmes de Cholestérol, une grande taille, un poids anormal, peu d'anticorps et des vertiges.");

        when(patientClient.getPatientById(1)).thenReturn(patient);
        when(noteClient.getNotesByPatientId("1")).thenReturn(List.of(note));

        // Act
        AssessmentRisk risk = assessmentService.assessRisk(1);

        // Assert
        assertEquals(AssessmentRisk.EarlyOnset, risk);
    }
}

