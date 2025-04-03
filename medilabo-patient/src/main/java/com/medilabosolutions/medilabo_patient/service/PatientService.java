package com.medilabosolutions.medilabo_patient.service;

import com.medilabosolutions.medilabo_patient.exceptions.PatientNotFoundException;
import com.medilabosolutions.medilabo_patient.model.Patient;
import com.medilabosolutions.medilabo_patient.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    // Ajouter un patient
    public Patient createPatient(Patient patient) {
        if (patient == null) {
            throw new IllegalArgumentException("Patient must not be null");
        }
        return patientRepository.save(patient);
    }

    // Récupérer un patient par ID
    public Patient getPatientById(int id) {
        Optional<Patient> patientOpt = patientRepository.findById(id);
        if(patientOpt.isEmpty())
        {
            throw new PatientNotFoundException("Le patient avec l'ID " + id + " n'existe pas");
        }
        return patientOpt.get();

    }

    // Récupérer un patient par nom et prénom
    public Patient getPatientByName(String firstName, String lastName) {
        Optional<Patient> patientOpt = patientRepository.findByFirstNameAndLastName(firstName, lastName);
        if (patientOpt.isEmpty()) {
            throw new PatientNotFoundException("Aucun patient trouvé avec le nom " + lastName + " et le prénom " + firstName);
        }
        return patientOpt.get();
    }


    // Récupérer tous les patients ayant un certain nom de famille
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    // Mettre à jour un patient
    public Patient updatePatient(int id, Patient patientDetailsUpdated) {

        Patient patientToUpdate = getPatientById(id);

        patientToUpdate.setFirstName(patientDetailsUpdated.getFirstName());
        patientToUpdate.setLastName(patientDetailsUpdated.getLastName());
        patientToUpdate.setDateOfBirth(patientDetailsUpdated.getDateOfBirth());
        patientToUpdate.setGender(patientDetailsUpdated.getGender());
        patientToUpdate.setAddress(patientDetailsUpdated.getAddress());
        patientToUpdate.setPhoneNumber(patientDetailsUpdated.getPhoneNumber());
        return patientRepository.save(patientToUpdate);
    }

    // Supprimer un patient
    public void deletePatient(int id) {
        Patient patient = getPatientById(id);

        patientRepository.delete(patient);
    }

}

