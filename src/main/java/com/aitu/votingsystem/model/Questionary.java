package com.aitu.votingsystem.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "questionary")
public class Questionary {
    @Id
    @Column(name = "question_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer questionId;
    @Column(name = "question")
    private String question;

    @OneToMany(mappedBy = "questionary", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<AnswerOptions> answerOptions = new ArrayList<>();

    @OneToMany(mappedBy = "survey_questionary", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<SurveyQuestionary> surveyQuestionary = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "questionary")
    private Results results;

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<AnswerOptions> getAnswerOptions() {
        return answerOptions;
    }

    public Results getResults() {
        return results;
    }

    public void setResults(Results results) {
        this.results = results;
    }
}
