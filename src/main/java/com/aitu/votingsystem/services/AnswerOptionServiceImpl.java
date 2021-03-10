package com.aitu.votingsystem.services;

import com.aitu.votingsystem.model.AnswerOptions;
import com.aitu.votingsystem.repository.AnswerOptionRepository;
import com.aitu.votingsystem.services.interfaces.AnswerOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AnswerOptionServiceImpl implements AnswerOptionService {
    @Autowired
    private AnswerOptionRepository answerOptionRepository;


    @Override
    public List<AnswerOptions> getAllAnswers() {
        return answerOptionRepository.findAll();
    }
}
