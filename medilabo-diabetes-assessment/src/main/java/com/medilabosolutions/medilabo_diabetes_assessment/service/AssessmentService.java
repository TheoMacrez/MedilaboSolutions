package com.medilabosolutions.medilabo_diabetes_assessment.service;

import com.medilabosolutions.medilabo_diabetes_assessment.client.NoteClient;
import com.medilabosolutions.medilabo_diabetes_assessment.client.PatientClient;
import com.medilabosolutions.medilabo_diabetes_assessment.dto.Gender;
import com.medilabosolutions.medilabo_diabetes_assessment.dto.NoteDto;
import com.medilabosolutions.medilabo_diabetes_assessment.dto.PatientDto;
import com.medilabosolutions.medilabo_diabetes_assessment.model.AssessmentRisk;
import com.medilabosolutions.medilabo_diabetes_assessment.util.TriggerWords;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
public class AssessmentService {

    private final PatientClient patientClient;
    private final NoteClient noteClient;

    public AssessmentService(PatientClient patientClient, NoteClient noteClient) {
        this.patientClient = patientClient;
        this.noteClient = noteClient;
    }

    public AssessmentRisk assessRisk(int patId) {
        PatientDto patient = patientClient.getPatientById(patId);
        List<NoteDto> notes = noteClient.getNotesByPatientId(String.valueOf(patId));

        if(notes.isEmpty())
            return AssessmentRisk.None;

        int age = calculateAge(patient.getDateOfBirth());

        int triggerWordCount = 0;
        for (NoteDto note : notes) {
            triggerWordCount += countTriggerWords(note.getContent());
        }

        return determineRisk(age,triggerWordCount,patient.getGender()); // (exemple)
    }

    private int countTriggerWords(String content)
    {
        int count = 0;
        for (String trigger : TriggerWords.WORDS) {
            if (content != null && content.toLowerCase().contains(trigger.toLowerCase())) {
                count++;
            }
        }
        return count;
    }

    private int calculateAge(LocalDate birthDate) {
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

    private AssessmentRisk determineRisk(int age, int triggerCount, Gender gender)
    {
        if (triggerCount == 0) {
            return AssessmentRisk.None;
        }

        if (age > 30) {
            if (triggerCount >= 8) {
                return AssessmentRisk.EarlyOnset;
            } else if (triggerCount >= 6) {
                return AssessmentRisk.InDanger;
            } else if (triggerCount >= 2) {
                return AssessmentRisk.Borderline;
            }
        }
        else {
            switch (gender)
            {
                case M -> {
                    if (triggerCount >= 5) {
                        return AssessmentRisk.EarlyOnset;
                    } else if (triggerCount >= 3) {
                        return AssessmentRisk.InDanger;
                    }
                }
                case F,Other -> {
                    if (triggerCount >= 7) {
                        return AssessmentRisk.EarlyOnset;
                    } else if (triggerCount >= 4) {
                        return AssessmentRisk.InDanger;
                    }
                }

            }
        }
        return AssessmentRisk.None;
    }
}
