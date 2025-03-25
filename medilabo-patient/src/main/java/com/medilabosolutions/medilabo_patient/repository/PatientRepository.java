package com.medilabosolutions.medilabo_patient.repository;

import com.medilabosolutions.medilabo_patient.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    Optional<Patient> findByFirstNameAndLastName(String firstName, String lastName);

}
