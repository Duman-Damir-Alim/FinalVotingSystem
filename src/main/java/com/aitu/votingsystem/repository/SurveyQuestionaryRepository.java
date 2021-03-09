package com.aitu.votingsystem.repository;

import com.aitu.votingsystem.model.SurveyQuestionary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SurveyQuestionaryRepository extends JpaRepository<SurveyQuestionary, Integer> {

}
