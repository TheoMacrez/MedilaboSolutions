package com.medilabosolutions.medilabo_patient.service;

import com.medilabosolutions.medilabo_patient.exceptions.PatientNotFoundException;
import com.medilabosolutions.medilabo_patient.model.Patient;
import com.medilabosolutions.medilabo_patient.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service gérant les opérations relatives aux patients.
 */
@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    /**
     * Crée un nouveau patient.
     *
     * @param patient Le patient à créer.
     * @return Le patient créé.
     */
    public Patient createPatient(Patient patient) {
        if (patient == null) {
            throw new IllegalArgumentException("Patient must not be null");
        }
        return patientRepository.save(patient);
    }

    /**
     * Récupère un patient par son identifiant.
     *
     * @param id L'identifiant du patient.
     * @return Le patient correspondant.
     * @throws PatientNotFoundException si aucun patient n'est trouvé.
     */
    public Patient getPatientById(int id) {
        Optional<Patient> patientOpt = patientRepository.findById(id);
        if (patientOpt.isEmpty()) {
            throw new PatientNotFoundException("Le patient avec l'ID " + id + " n'existe pas");
        }
        return patientOpt.get();
    }

    /**
     * Récupère un patient par son prénom et son nom.
     *
     * @param firstName Le prénom du patient.
     * @param lastName  Le nom du patient.
     * @return Le patient correspondant.
     * @throws PatientNotFoundException si aucun patient n'est trouvé.
     */
    public Patient getPatientByName(String firstName, String lastName) {
        Optional<Patient> patientOpt = patientRepository.findByFirstNameAndLastName(firstName, lastName);
        if (patientOpt.isEmpty()) {
            throw new PatientNotFoundException("Aucun patient trouvé avec le nom " + lastName + " et le prénom " + firstName);
        }
        return patientOpt.get();
    }

    /**
     * Récupère la liste de tous les patients.
     *
     * @return La liste des patients.
     */
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    /**
     * Met à jour les informations d'un patient existant.
     *
     * @param id                   L'identifiant du patient à mettre à jour.
     * @param patientDetailsUpdated Les nouvelles informations du patient.
     * @return Le patient mis à jour.
     */
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

    /**
     * Supprime un patient par son identifiant.
     *
     * @param id L'identifiant du patient à supprimer.
     */
    public void deletePatient(int id) {
        Patient patient = getPatientById(id);
        patientRepository.delete(patient);
    }

}
