package com.aitu.votingsystem.repository;

import com.aitu.votingsystem.model.SurveyQuestionary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SurveyQuestionaryRepository extends JpaRepository<SurveyQuestionary, Integer> {
    @Query(value = "SELECT * FROM survey_questionary WHERE survey_id = ?", nativeQuery = true)
    List<SurveyQuestionary> getAllBySurveyId(@Param("id") Integer id);
}
