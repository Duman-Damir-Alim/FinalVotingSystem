package com.aitu.votingsystem.repository;

import com.aitu.votingsystem.model.AnswerOptions;
import com.aitu.votingsystem.model.Results;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResultRepository extends JpaRepository<Results, Integer> {
    List<Results> findResultsByAnswerOption(AnswerOptions answerOption);
}
