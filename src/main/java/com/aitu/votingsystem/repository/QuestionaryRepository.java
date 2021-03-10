package com.aitu.votingsystem.repository;

import com.aitu.votingsystem.model.Questionary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionaryRepository extends JpaRepository<Questionary, Integer> {

}
