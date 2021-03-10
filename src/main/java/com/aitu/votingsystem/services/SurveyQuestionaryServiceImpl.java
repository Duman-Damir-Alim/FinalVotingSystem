package com.aitu.votingsystem.services;

import com.aitu.votingsystem.model.SurveyQuestionary;
import com.aitu.votingsystem.repository.SurveyQuestionaryRepository;
import com.aitu.votingsystem.services.interfaces.SurveyQuestionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SurveyQuestionaryServiceImpl implements SurveyQuestionaryService {
    @Autowired
    private SurveyQuestionaryRepository surveyQuestionaryRepository;

    @Override
    public List<SurveyQuestionary> getAllSurveyQuestionary(int id) {
        return surveyQuestionaryRepository.getAllBySurveyId(id);
    }
}
