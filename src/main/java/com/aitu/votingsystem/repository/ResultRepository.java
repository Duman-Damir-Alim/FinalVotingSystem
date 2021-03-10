package com.aitu.votingsystem.repository;

import com.aitu.votingsystem.model.Results;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultRepository extends JpaRepository<Results, Integer> {
}
