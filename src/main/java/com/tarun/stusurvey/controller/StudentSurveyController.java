package com.tarun.stusurvey.controller;

import com.tarun.stusurvey.Entity.StudentSurvey;
import com.tarun.stusurvey.service.StudentSurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/surveys")
public class StudentSurveyController {

    private final StudentSurveyService service;

    @Autowired
    public StudentSurveyController(StudentSurveyService service) {
        this.service = service;
    }

    //  Get all surveys
    @GetMapping
    public List<StudentSurvey> getAllSurveys() {
        return service.getAllSurveys();
    }

    //  Get a specific survey by ID
    @GetMapping("/{id}")
    public ResponseEntity<StudentSurvey> getSurveyById(@PathVariable Long id) {
        Optional<StudentSurvey> survey = service.getSurveyById(id);
        return survey.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    //  Create a new survey
    @PostMapping
    public ResponseEntity<StudentSurvey> createSurvey(@RequestBody StudentSurvey survey) {
        StudentSurvey savedSurvey = service.saveSurvey(survey);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedSurvey);
    }

    //  Update an existing survey
    @PutMapping("/{id}")
    public ResponseEntity<StudentSurvey> updateSurvey(@PathVariable Long id, @RequestBody StudentSurvey updatedSurvey) {
        Optional<StudentSurvey> existingSurvey = service.getSurveyById(id);

        if (existingSurvey.isPresent()) {
            StudentSurvey survey = existingSurvey.get();

            // Set new values
            survey.setFirstName(updatedSurvey.getFirstName());
            survey.setLastName(updatedSurvey.getLastName());
            survey.setStreetAddress(updatedSurvey.getStreetAddress());
            survey.setCity(updatedSurvey.getCity());
            survey.setState(updatedSurvey.getState());
            survey.setZip(updatedSurvey.getZip());
            survey.setTelephone(updatedSurvey.getTelephone());
            survey.setEmail(updatedSurvey.getEmail());
            survey.setSurveyDate(updatedSurvey.getSurveyDate());
            survey.setLiked(updatedSurvey.getLiked());
            survey.setInterested(updatedSurvey.getInterested());
            survey.setRecommendation(updatedSurvey.getRecommendation());

            StudentSurvey savedSurvey = service.saveSurvey(survey);
            return ResponseEntity.ok(savedSurvey);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    //  Delete a survey by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSurvey(@PathVariable Long id) {
        Optional<StudentSurvey> survey = service.getSurveyById(id);
        if (survey.isPresent()) {
            service.deleteSurvey(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
