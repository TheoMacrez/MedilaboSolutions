package com.medilabosolutions.medilabo_diabetes_assessment.util;

import java.util.List;

public class TriggerWords {

    private TriggerWords() {
        // Constructeur privé pour empêcher l'instanciation
    }

    public static final List<String> WORDS = List.of(
            "Hémoglobine A1C",
            "Microalbumine",
            "Taille",
            "Poids",
            "Fumeur",
            "Fumeuse",
            "Anormal",
            "Anormale",
            "Cholestérol",
            "Vertiges",
            "Rechute",
            "Réaction",
            "Anticorps"
    );
}
