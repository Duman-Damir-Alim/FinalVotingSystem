package com.aitu.votingsystem.repository;

import com.aitu.votingsystem.model.Questionary;
import com.aitu.votingsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionaryRepository  extends JpaRepository<Questionary, Integer> {
    @Query("SELECT q.questionId FROM Questionary q WHERE q.question = :question")
    int getQuestionId(@Param("question") String question);
}
