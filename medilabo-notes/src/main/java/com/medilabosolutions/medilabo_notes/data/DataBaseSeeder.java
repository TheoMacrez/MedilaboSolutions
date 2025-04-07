package com.medilabosolutions.medilabo_notes.data;

import com.medilabosolutions.medilabo_notes.model.Note;
import com.medilabosolutions.medilabo_notes.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class DataBaseSeeder implements CommandLineRunner {

    @Autowired
    private  NoteRepository noteRepository;

    @Override
    public void run(String... args) throws Exception {
        if (noteRepository.count() == 0) {  // <-- ici on vérifie que la collection est vide
            List<Note> notes = List.of(
                    new Note("1", "TestNone", "Le patient déclare qu'il 'se sent très bien' Poids égal ou inférieur au poids recommandé"),
                    new Note("2", "TestBorderline", "Le patient déclare qu'il ressent beaucoup de stress au travail Il se plaint également que son audition est anormale dernièrement"),
                    new Note("2", "TestBorderline", "Le patient déclare avoir fait une réaction aux médicaments au cours des 3 derniers mois Il remarque également que son audition continue d'être anormale"),
                    new Note("3", "TestInDanger", "Le patient déclare qu'il fume depuis peu"),
                    new Note("3", "TestInDanger", "Le patient déclare qu'il est fumeur et qu'il a cessé de fumer l'année dernière Il se plaint également de crises d’apnée respiratoire anormales Tests de laboratoire indiquant un taux de cholestérol LDL élevé"),
                    new Note("4", "TestEarlyOnset", "Le patient déclare qu'il lui est devenu difficile de monter les escaliers Il se plaint également d’être essoufflé Tests de laboratoire indiquant que les anticorps sont élevés Réaction aux médicaments"),
                    new Note("4", "TestEarlyOnset", "Le patient déclare qu'il a mal au dos lorsqu'il reste assis pendant longtemps"),
                    new Note("4", "TestEarlyOnset", "Le patient déclare avoir commencé à fumer depuis peu Hémoglobine A1C supérieure au niveau recommandé"),
                    new Note("4", "TestEarlyOnset", "Taille, Poids, Cholestérol, Vertige et Réaction")
            );

            noteRepository.saveAll(notes);
            System.out.println("✅ Notes initiales insérées avec succès !");
        } else {
            System.out.println("ℹ️ La base de données contient déjà des notes. Aucun ajout effectué.");
        }



    }
}
