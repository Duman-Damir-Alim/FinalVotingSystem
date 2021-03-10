package com.aitu.votingsystem.repository;

import com.aitu.votingsystem.model.AnswerOptions;
import com.aitu.votingsystem.model.Results;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResultRepository extends JpaRepository<Results, Integer> {
    List<Results> findResultsByAnswerOption(AnswerOptions answerOption);

    @Query("SELECT DISTINCT r.survey.title FROM Results r WHERE r.user.id = :userId")
    List<String> getSurveyTitleByUserId(@Param("userId") Integer userId);

    @Query("SELECT DISTINCT r.survey.description FROM Results r WHERE r.user.id = :userId")
    List<String> getSurveyDescriptionByUserId(@Param("userId") Integer userId);

}
