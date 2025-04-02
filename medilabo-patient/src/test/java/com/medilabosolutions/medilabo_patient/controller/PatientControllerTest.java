package com.medilabosolutions.medilabo_patient.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.medilabosolutions.medilabo_patient.exceptions.GlobalExceptionHandler;
import com.medilabosolutions.medilabo_patient.model.Gender;
import com.medilabosolutions.medilabo_patient.model.Patient;
import com.medilabosolutions.medilabo_patient.service.PatientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.access.SecurityConfig;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class PatientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private PatientService patientService; // Utilisez @Mock au lieu de @Autowired

    @InjectMocks
    private PatientController patientController; // Injectez le mock dans le contrôleur

    @Autowired
    private WebApplicationContext context;


    @Autowired
    private ObjectMapper objectMapper;

    private Patient patient;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialise les mocks
        mockMvc = MockMvcBuilders.standaloneSetup(patientController).setControllerAdvice(new GlobalExceptionHandler()).build(); // Configure MockMvc
//        mockMvc = MockMvcBuilders.webAppContextSetup(context)
//                .build();

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
    void shouldGetAllPatients() throws Exception {
        when(patientService.getAllPatients()).thenReturn(List.of(patient));

        mockMvc.perform(get("/patients"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].firstName").value("John"));
    }

    @Test
    void shouldGetPatientById() throws Exception {
        when(patientService.getPatientById(1L)).thenReturn(patient);

        mockMvc.perform(get("/patients/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("John"));
    }

    @Test
    void shouldCreatePatient() throws Exception {
        when(patientService.createPatient(any(Patient.class))).thenReturn(patient);

        String patientJson = objectMapper.writeValueAsString(patient);
        System.out.println(patientJson); // Vérifiez le JSON

        mockMvc.perform(post("/patients/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(patientJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("John"));
    }



    @Test
    void shouldUpdatePatient() throws Exception {
        when(patientService.updatePatient(Mockito.eq(1L), any(Patient.class))).thenReturn(patient);

        mockMvc.perform(put("/patients/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(patient)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("John"));
    }

    @Test
    void shouldDeletePatient() throws Exception {
        mockMvc.perform(delete("/patients/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void shouldFailWhenCreatingPatientWithInvalidData() throws Exception {

        Patient invalidPatient = Patient.builder()
                .id(1L)
                .firstName("")
                .lastName("")
                .dateOfBirth(LocalDate.now().plusDays(1))
                .gender(null)
                .address("123 Street")
                .phoneNumber("0123456789")
                .build();

        mockMvc.perform(post("/patients/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidPatient)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.firstName").value("Le prénom est obligatoire"))
                .andExpect(jsonPath("$.lastName").value("Le nom est obligatoire"))
                .andExpect(jsonPath("$.dateOfBirth").value("La date de naissance doit être dans le passé"))
                .andExpect(jsonPath("$.gender").value("Le genre est obligatoire"));

    }

}

