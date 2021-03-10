package com.aitu.votingsystem.services;

import com.aitu.votingsystem.model.Survey;
import com.aitu.votingsystem.repository.SurveyRepository;
import com.aitu.votingsystem.services.interfaces.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SurveyServiceImpl implements SurveyService {
    @Autowired
    private SurveyRepository surveyRepository;

    @Override
    public List<Survey> getAllSurveys() {
        return surveyRepository.findAll();
    }

    @Override
    public Survey getSurveyById(int id) {
        Optional<Survey> optional = surveyRepository.findById(id);
        Survey survey = null;
        if (optional.isPresent()) {
            survey = optional.get();
        } else {
            throw new RuntimeException("Survey not found for id :: " + id);
        }
        return survey;
    }
}
