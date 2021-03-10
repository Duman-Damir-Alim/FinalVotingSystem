package com.aitu.votingsystem.controller;

import com.aitu.votingsystem.model.AnswerOptions;
import com.aitu.votingsystem.model.Questionary;
import com.aitu.votingsystem.model.Survey;
import com.aitu.votingsystem.model.SurveyQuestionary;
import com.aitu.votingsystem.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminPageController {
    @Autowired
    private QuestionaryRepository questionaryRepository;
    @Autowired
    private AnswerOptionRepository answerOptionsRepository;
    @Autowired
    private SurveyQuestionaryRepository surveyQuestionaryRepository;
    @Autowired
    private SurveyRepository surveyRepository;
    @Autowired
    private TeacherRepository teacherRepository;

    @GetMapping()
    public String goToAdminPage(Model model) {
        List<Survey> surveys = surveyRepository.findAll();
        model.addAttribute("surveys", surveys);
        return "adminPage";
    }

    // we have 5 answer options for all questions!
    @PostMapping("/addQuestionAndAnswerOptionsToSurvey")
    public String addQuestionAndAnswerOptionsToSurvey(
            @ModelAttribute("survey_id") int survey_id,
            @ModelAttribute("question") String question,
            @ModelAttribute("option1") String option1,
            @ModelAttribute("option2") String option2,
            @ModelAttribute("option3") String option3,
            @ModelAttribute("option4") String option4,
            @ModelAttribute("option5") String option5,
            Model model) {
        Questionary questionary = new Questionary();
        questionary.setQuestion(question);
        questionaryRepository.save(questionary);

        saveAnswerOption(questionary, option1);
        saveAnswerOption(questionary, option2);
        saveAnswerOption(questionary, option3);
        saveAnswerOption(questionary, option4);
        saveAnswerOption(questionary, option5);

        SurveyQuestionary surveyQuestionary = new SurveyQuestionary();
        Survey survey = surveyRepository.getOne(survey_id);

        surveyQuestionary.setSurvey_questionary(questionary);
        survey.setSurvey_id(survey_id);
        surveyQuestionary.setSurvey(survey);

        surveyQuestionaryRepository.save(surveyQuestionary);

        model.addAttribute("add_question_message", "added successfully");
        return "adminPage";
    }

    private void saveAnswerOption(Questionary questionary, String option) {
        AnswerOptions answerOption = new AnswerOptions();
        answerOption.setQuestionary(questionary);
        answerOption.setOption(option);
        answerOptionsRepository.save(answerOption);
    }

    @PostMapping("/deleteQuestion")
    public String deleteQuestion(
            @ModelAttribute("question_id") int question_id,
            Model model) {
        Questionary questionary = questionaryRepository.getOne(question_id);
        questionaryRepository.delete(questionary);

        model.addAttribute("delete_question_message", "successfully deleted");
        return "adminPage";
    }

    @PostMapping("/updateQuestion")
    public String updateQuestion(
            @ModelAttribute("question_id") int questionId,
            @ModelAttribute("question") String question,
            Model model
    ) {
        Questionary questionary = questionaryRepository.getOne(questionId);
        questionary.setQuestion(question);
        questionaryRepository.save(questionary);
        model.addAttribute("update_question_message", "updated successfully");
        return "adminPage";
    }

    @PostMapping("/updateAnswersInQuestion")
    @Transactional //transactional allows to make custom delete and update operations
    public String updateAnswersInQuestion(
            @ModelAttribute("question_id") int question_id,
            @ModelAttribute("option1") String option1,
            @ModelAttribute("option2") String option2,
            @ModelAttribute("option3") String option3,
            @ModelAttribute("option4") String option4,
            @ModelAttribute("option5") String option5,
            Model model) {
        answerOptionsRepository.deleteAnswerOptionsByQuestionId(question_id);
        Questionary questionary = questionaryRepository.getOne(question_id);

        saveAnswerOption(questionary, option1);
        saveAnswerOption(questionary, option2);
        saveAnswerOption(questionary, option3);
        saveAnswerOption(questionary, option4);
        saveAnswerOption(questionary, option5);

        model.addAttribute("update_answers_message", "updated successfully");
        return "adminPage";
    }

    @PostMapping("/createSurvey")
    public String createSurvey(
            @ModelAttribute("description") String description,
            @ModelAttribute("title") String title,
            @ModelAttribute("teacher_id") int teacher_id,
            Model model
    ) {
        Survey survey = new Survey();
        survey.setDescription(description);
        survey.setTitle(title);
        survey.setTeacher(teacherRepository.getOne(teacher_id));
        surveyRepository.save(survey);
        model.addAttribute("create_survey_message", "created successfully");
        return "adminPage";
    }
}
