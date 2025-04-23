package com.medilabosolutions.medilabo_patient.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

/**
 * Représente un patient dans le système et la base de données SQL.
 */
@Entity
@Table(name = "patient")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Patient {

    /**
     * Identifiant unique du patient.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * Prénom du patient.
     */
    @NotBlank(message = "Le prénom est obligatoire")
    @Column(nullable = false)
    private String firstName;

    /**
     * Nom de famille du patient.
     */
    @NotBlank(message = "Le nom est obligatoire")
    @Column(nullable = false)
    private String lastName;

    /**
     * Date de naissance du patient.
     */
    @NotNull(message = "La date de naissance est obligatoire")
    @Past(message = "La date de naissance doit être dans le passé")
    @Column(nullable = false)
    private LocalDate dateOfBirth;

    /**
     * Genre du patient (MASCULIN ou FEMININ).
     */
    @NotNull(message = "Le genre est obligatoire")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    /**
     * Adresse du patient.
     */
    private String address;

    /**
     * Numéro de téléphone du patient.
     */
    private String phoneNumber;
}
