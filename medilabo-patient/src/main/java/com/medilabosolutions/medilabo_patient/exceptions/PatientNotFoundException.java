package com.medilabosolutions.medilabo_patient.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception levée lorsqu'un patient n'est pas trouvé.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class PatientNotFoundException extends RuntimeException {

    /**
     * Construit une nouvelle exception avec un message spécifique.
     *
     * @param message Le message décrivant l'erreur.
     */
    public PatientNotFoundException(String message) {
        super(message);
    }
}
