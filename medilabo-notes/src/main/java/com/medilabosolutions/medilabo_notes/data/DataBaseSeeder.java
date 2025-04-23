package com.medilabosolutions.medilabo_notes.data;

import com.medilabosolutions.medilabo_notes.model.Note;
import com.medilabosolutions.medilabo_notes.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * Classe permettant d'initialiser la base de données avec des données de test au lancement de l'application.
 */
@Component
public class DataBaseSeeder implements CommandLineRunner {

    @Autowired
    private NoteRepository noteRepository;

    /**
     * Méthode exécutée au démarrage de l'application pour insérer des notes par défaut si la base est vide.
     *
     * @param args arguments d'exécution (non utilisés ici)
     * @throws Exception en cas d'erreur
     */
    @Override
    public void run(String... args) throws Exception {
        if (noteRepository.count() == 0) {
            List<Note> notes = List.of(
                    new Note("1", "Le patient déclare qu'il 'se sent très bien' Poids égal ou inférieur au poids recommandé"),
                    new Note("2", "Le patient déclare qu'il ressent beaucoup de stress au travail Il se plaint également que son audition est anormale dernièrement"),
                    new Note("2", "Le patient déclare avoir fait une réaction aux médicaments au cours des 3 derniers mois Il remarque également que son audition continue d'être anormale"),
                    new Note("3", "Le patient déclare qu'il fume depuis peu"),
                    new Note("3", "Le patient déclare qu'il est fumeur et qu'il a cessé de fumer l'année dernière Il se plaint également de crises d’apnée respiratoire anormales Tests de laboratoire indiquant un taux de cholestérol LDL élevé"),
                    new Note("4", "Le patient déclare qu'il lui est devenu difficile de monter les escaliers Il se plaint également d’être essoufflé Tests de laboratoire indiquant que les anticorps sont élevés Réaction aux médicaments"),
                    new Note("4", "Le patient déclare qu'il a mal au dos lorsqu'il reste assis pendant longtemps"),
                    new Note("4", "Le patient déclare avoir commencé à fumer depuis peu Hémoglobine A1C supérieure au niveau recommandé"),
                    new Note("4", "Taille, Poids, Cholestérol, Vertige et Réaction")
            );

            noteRepository.saveAll(notes);
            System.out.println("✅ Notes initiales insérées avec succès !");
        } else {
            System.out.println("ℹ️ La base de données contient déjà des notes. Aucun ajout effectué.");
        }
    }
}
