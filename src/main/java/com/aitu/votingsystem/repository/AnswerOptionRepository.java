package com.aitu.votingsystem.repository;

import com.aitu.votingsystem.model.AnswerOptions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerOptionRepository extends JpaRepository<AnswerOptions, Integer> {

}
