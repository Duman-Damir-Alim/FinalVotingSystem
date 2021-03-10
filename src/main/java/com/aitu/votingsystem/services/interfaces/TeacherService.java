package com.aitu.votingsystem.services.interfaces;

import com.aitu.votingsystem.model.Teacher;

import java.util.List;
import java.util.Optional;

public interface TeacherService {
    List<Teacher> getAllTeachers();
    Optional<Teacher> findById(int id);
}
