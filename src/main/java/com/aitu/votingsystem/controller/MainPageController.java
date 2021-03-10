package com.aitu.votingsystem.controller;


import com.aitu.votingsystem.ResultWrapper;
import com.aitu.votingsystem.model.*;
import com.aitu.votingsystem.repository.*;
import com.aitu.votingsystem.services.interfaces.SurveyQuestionaryService;
import com.aitu.votingsystem.services.interfaces.SurveyService;
import com.aitu.votingsystem.threads.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MainPageController {
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private SurveyService surveyService;
    @Autowired
    private SurveyQuestionaryService surveyQuestionaryService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ResultRepository resultRepository;
    @Autowired
    private AnswerOptionRepository answerOptionRepository;


    @GetMapping("/")
    public String index(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        List<Survey> surveys = surveyService.getAllSurveys();
        model.addAttribute("surveys", surveys);
        if (!(auth instanceof AnonymousAuthenticationToken))
            return "main";

        return "redirect:/login";
    }

    @GetMapping("/teachersAndSubjects")
    public String showTeachersAndSubjects(Model model) {
        List<Teacher> teachers = teacherRepository.findAll();
        List<Subject> subjects = subjectRepository.findAll();

        model.addAttribute("teachers", teachers);
        model.addAttribute("subjects", subjects);

        return "teachersAndSubjects";
    }

    @GetMapping("/showSurveyStatistics/{id}")
    public String showSurveyStatistics(@PathVariable(value = "id") Integer id, Model model, Principal principal){
        User user = userRepository.getUserByUsername(principal.getName());
        Survey survey = surveyService.getSurveyById(id);
        List<SurveyQuestionary> surveyQuestionaries = surveyQuestionaryService.getAllSurveyQuestionary(id);
        for (SurveyQuestionary surveyQuestionary : surveyQuestionaries) {
            System.out.println("Hello");
            int sum = 0;
            for (AnswerOptions answerOptions : surveyQuestionary.getSurvey_questionary().getAnswerOptions()){
                //System.out.println(answerOptions.getId() + " " + answerOptions.getOption());
                sum += resultRepository.findResultsByAnswerOption(answerOptions).size();
                //System.out.println(resultRepository.findResultsByAnswerOption(answerOptions).size());
            }
            System.out.println(sum);
            for (AnswerOptions answerOptions : surveyQuestionary.getSurvey_questionary().getAnswerOptions()){
                int count = resultRepository.findResultsByAnswerOption(answerOptions).size();
                answerOptions.setPercentage((count * 100) / sum);
            }
        }
        model.addAttribute("questions", surveyQuestionaries);
        model.addAttribute("surveyOne", survey);
        return "statistics";
    }

    @GetMapping("/showSurvey/{id}")
    public String getSurveyById(@PathVariable(value = "id") Integer id, Model model, Principal principal) {
        User user = userRepository.getUserByUsername(principal.getName());
        Survey survey = surveyService.getSurveyById(id);
        List<SurveyQuestionary> surveyQuestionaries = surveyQuestionaryService.getAllSurveyQuestionary(id);
        model.addAttribute("questions", surveyQuestionaries);
        model.addAttribute("surveyOne", survey);
        ArrayList<Results> results = new ArrayList<>();
        for (int i = 0; i < surveyQuestionaries.size(); ++i) {
            results.add(new Results(user));
        }
        ResultWrapper resultWrapper = new ResultWrapper();
        resultWrapper.setResults(results);
        model.addAttribute("resultWrapper", resultWrapper);
        return "survey";
    }

    @PostMapping("/submitSurvey")
    public String submitSurvey(
            @ModelAttribute("option0") Integer option_0,
            @ModelAttribute("option1") Integer option_1,
            @ModelAttribute("option2") Integer option_2,
            @ModelAttribute("option3") Integer option_3,
            @ModelAttribute("option4") Integer option_4, Model model) {
        System.out.println(option_0);
        if (option_3 != null) {
            System.out.println(option_3);
        }
        if (option_4 != null) {
            System.out.println(option_4);
        }
        System.out.println(option_1);
        System.out.println(option_2);

        //System.out.println("Value: " + answerOptions);
        return "redirect:/";
    }

    @GetMapping("/complaint")
    public String showComplaintPage() {
        return "complaints";
    }

    @PostMapping("/complaint")
    public String processComplaint(@ModelAttribute("text") String text, Principal principal, Model model) throws InterruptedException, IOException, ClassNotFoundException {
        User user = userRepository.getUserByUsername(principal.getName());
        Client clientSocket = new Client();
        String responseMessage = clientSocket.processComplaintAndGetResponse(text);
        model.addAttribute("user", user);
        model.addAttribute("message", responseMessage);
        return "complaints";
    }
}

