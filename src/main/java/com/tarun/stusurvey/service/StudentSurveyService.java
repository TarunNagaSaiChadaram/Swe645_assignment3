package com.tarun.stusurvey.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tarun.stusurvey.Entity.StudentSurvey;
import com.tarun.stusurvey.repository.StudentsurveyRepository;  // Ensure correct repository name

import java.util.List;
import java.util.Optional;

@Service
public class StudentSurveyService {

    private final StudentsurveyRepository repository;  // Ensure correct repository name

    @Autowired
    public StudentSurveyService(StudentsurveyRepository repository) {
        this.repository = repository;
    }

    public List<StudentSurvey> getAllSurveys() {
        return repository.findAll();
    }

    public Optional<StudentSurvey> getSurveyById(Long id) {  // Use Long instead of Integer
        return repository.findById(id);
    }

    public StudentSurvey saveSurvey(StudentSurvey survey) {
        return repository.save(survey);
    }

    public void deleteSurvey(Long id) {  // Use Long instead of Integer
        repository.deleteById(id);
    }
}
