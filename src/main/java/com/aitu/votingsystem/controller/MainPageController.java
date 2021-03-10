package com.aitu.votingsystem.controller;


import com.aitu.votingsystem.ResultWrapper;
import com.aitu.votingsystem.model.*;
import com.aitu.votingsystem.repository.SubjectRepository;
import com.aitu.votingsystem.repository.SurveyRepository;
import com.aitu.votingsystem.repository.TeacherRepository;
import com.aitu.votingsystem.repository.UserRepository;
import com.aitu.votingsystem.services.interfaces.SurveyQuestionaryService;
import com.aitu.votingsystem.services.interfaces.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.Array;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class MainPageController {
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private SurveyService surveyService;
    @Autowired
    private SurveyRepository surveyRepository;
    @Autowired
    private SurveyQuestionaryService surveyQuestionaryService;
    @Autowired
    private UserRepository userRepository;


    @GetMapping("/")
    public String index(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        List<Survey> surveys = surveyService.getAllSurveys();
        model.addAttribute("surveys", surveys);
        if (!(auth instanceof AnonymousAuthenticationToken))
            return "main";

        // if it is not authenticated, then go to the index...
        // other things ...
        return "redirect:/login";
    }

    @GetMapping("/teachersAndSubjects")
    public String showTeachersAndSubjects(Model model) {
        List<Teacher> teachers =  teacherRepository.findAll();
        List<Subject> subjects =  subjectRepository.findAll();

        model.addAttribute("teachers", teachers);
        model.addAttribute("subjects", subjects);

        return "teachersAndSubjects";
    }

//    @RequestMapping("survey")
//    public String goToSurveyPage(){
//        return "survey";
//    }


    @GetMapping("/showSurvey/{id}")
    public String getSurveyById(@PathVariable(value = "id") Integer id, Model model, Principal principal){
        User user = userRepository.getUserByUsername(principal.getName());
        Survey survey = surveyService.getSurveyById(id);
        List<SurveyQuestionary> surveyQuestionaries = surveyQuestionaryService.getAllSurveyQuestionary(id);
        model.addAttribute("questions", surveyQuestionaries);
        model.addAttribute("surveyOne", survey);
        ArrayList<Results> results = new ArrayList<>();
        for (int i = 0; i < surveyQuestionaries.size(); ++i){
            results.add(new Results(user));
        }
        ResultWrapper resultWrapper = new ResultWrapper();
        resultWrapper.setResults(results);
        model.addAttribute("resultWrapper", resultWrapper);
        return "survey";
    }

    @PostMapping("/submitSurvey")
    public String submitSurvey(ArrayList<Results> results){
        System.out.println("Value: " + results);
        return "redirect:mainPage";
    }
}

