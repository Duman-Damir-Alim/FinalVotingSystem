package com.aitu.votingsystem.services.interfaces;

import com.aitu.votingsystem.model.Survey;

import java.util.List;

public interface SurveyService {
    List<Survey> getAllSurveys();

    Survey getSurveyById(int id);
}
