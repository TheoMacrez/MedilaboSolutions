package com.medilabosolutions.medilabo_diabetes_assessment.client;

import com.medilabosolutions.medilabo_diabetes_assessment.config.FeignClientTokenInterceptor;
import com.medilabosolutions.medilabo_diabetes_assessment.dto.NoteDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Client Feign pour communiquer avec le service de notes médicales.
 */
@FeignClient(name = "note-service", url = "${note.service.url}", configuration = FeignClientTokenInterceptor.class)
public interface NoteClient {

    /**
     * Récupère toutes les notes associées à un patient donné.
     *
     * @param patId identifiant du patient
     * @return liste de {@link NoteDto}
     */
    @GetMapping
    List<NoteDto> getNotesByPatientId(@RequestParam String patId);
}

