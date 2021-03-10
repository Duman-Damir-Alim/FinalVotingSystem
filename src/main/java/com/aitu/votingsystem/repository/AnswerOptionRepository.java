package com.aitu.votingsystem.repository;

import com.aitu.votingsystem.model.AnswerOptions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AnswerOptionRepository extends JpaRepository<AnswerOptions, Integer> {
    @Modifying
    @Query("delete from AnswerOptions a where a.questionary.questionId = :questionId")
    void deleteAnswerOptionsByQuestionId(@Param("questionId") int questionId);

}
