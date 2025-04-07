package com.medilabosolutions.medilabo_notes.repository;

import com.medilabosolutions.medilabo_notes.model.Note;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends MongoRepository<Note,String> {

    @Query(value = "{ 'patId': ?0 }")
    public List<Note> findByPatId(String patId);



}
