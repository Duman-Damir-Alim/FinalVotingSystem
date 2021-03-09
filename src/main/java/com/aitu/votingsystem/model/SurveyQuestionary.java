package com.aitu.votingsystem.model;

import javax.persistence.*;

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
}
