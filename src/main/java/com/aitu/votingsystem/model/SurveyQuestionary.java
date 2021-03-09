package com.aitu.votingsystem.model;

import javax.persistence.*;
import java.util.Optional;

@Entity
@Table(name = "survey_questionary")
public class SurveyQuestionary {
    @Id
    @Column(name = "survey_questionary_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer survey_questionary_id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "question_id")
    private Questionary survey_questionary;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "survey_id")
    private Survey survey;


    public Integer getSurvey_questionary_id() {
        return survey_questionary_id;
    }

    public void setSurvey_questionary_id(Integer survey_questionary_id) {
        this.survey_questionary_id = survey_questionary_id;
    }

    public Questionary getSurvey_questionary() {
        return survey_questionary;
    }

    public void setSurvey_questionary(Questionary survey_questionary) {
        this.survey_questionary = survey_questionary;
    }

    public Survey getSurvey() {
        return survey;
    }

    public void setSurvey(Survey survey) {
        this.survey = survey;
    }
}
