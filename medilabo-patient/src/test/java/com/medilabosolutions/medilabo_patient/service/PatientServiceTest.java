package com.medilabosolutions.medilabo_patient.service;

import com.medilabosolutions.medilabo_patient.exceptions.PatientNotFoundException;
import com.medilabosolutions.medilabo_patient.model.Gender;
import com.medilabosolutions.medilabo_patient.model.Patient;
import com.medilabosolutions.medilabo_patient.repository.PatientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PatientServiceTest {

    @Mock
    private PatientRepository patientRepository;

    @InjectMocks
    private PatientService patientService;

    private Patient patient;

    @BeforeEach
    void setUp() {
        patient = Patient.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .dateOfBirth(LocalDate.of(1990, 1, 1))
                .gender(Gender.M)
                .address("123 Street")
                .phoneNumber("0123456789")
                .build();
    }

    @Test
    void shouldCreatePatientSuccessfully() {
        when(patientRepository.save(any(Patient.class))).thenReturn(patient);

        Patient savedPatient = patientService.createPatient(patient);

        assertNotNull(savedPatient);
        assertEquals("John", savedPatient.getFirstName());
        verify(patientRepository, times(1)).save(any(Patient.class));
    }

    @Test
    void shouldReturnPatientById() {
        when(patientRepository.findById(1L)).thenReturn(Optional.of(patient));

        Patient foundPatient = patientService.getPatientById(1L);

        assertNotNull(foundPatient);
        assertEquals(1L, foundPatient.getId());
    }

    @Test
    void shouldThrowExceptionWhenPatientNotFoundById() {
        when(patientRepository.findById(2L)).thenReturn(Optional.empty());

        assertThrows(PatientNotFoundException.class, () -> patientService.getPatientById(2L));
    }

    @Test
    void shouldReturnAllPatients() {
        when(patientRepository.findAll()).thenReturn(List.of(patient));

        List<Patient> patients = patientService.getAllPatients();

        assertFalse(patients.isEmpty());
        assertEquals(1, patients.size());
    }

    @Test
    void shouldUpdatePatientSuccessfully() {
        Patient updatedDetails = Patient.builder()
                .firstName("Jane")
                .lastName("Doe")
                .dateOfBirth(LocalDate.of(1992, 2, 2))
                .gender(Gender.F)
                .address("456 Avenue")
                .phoneNumber("0987654321")
                .build();

        when(patientRepository.findById(1L)).thenReturn(Optional.of(patient));
        when(patientRepository.save(any(Patient.class))).thenReturn(updatedDetails);

        Patient updatedPatient = patientService.updatePatient(1L, updatedDetails);

        assertNotNull(updatedPatient);
        assertEquals("Jane", updatedPatient.getFirstName());
    }

    @Test
    void shouldDeletePatientSuccessfully() {
        when(patientRepository.findById(1L)).thenReturn(Optional.of(patient));
        doNothing().when(patientRepository).delete(patient);

        assertDoesNotThrow(() -> patientService.deletePatient(1L));
        verify(patientRepository, times(1)).delete(patient);
    }
}
