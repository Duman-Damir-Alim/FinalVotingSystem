package com.aitu.votingsystem.repository;

import com.aitu.votingsystem.model.Subject;
import com.aitu.votingsystem.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository  extends JpaRepository<Subject, Integer> {
}
