package com.tarun.stusurvey.repository;

import com.tarun.stusurvey.Entity.StudentSurvey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentsurveyRepository extends JpaRepository<StudentSurvey, Long> {
}