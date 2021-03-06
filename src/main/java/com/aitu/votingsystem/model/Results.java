package com.aitu.votingsystem.model;

import javax.persistence.*;

@Entity
@Table(name = "results")
public class Results {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public Results(User user) {
        this.user = user;
    }

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "answer_option_id", nullable = false)
    private AnswerOptions answerOption;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "survey_id")
    private Survey survey;

    public Results() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Survey getSurvey() {
        return survey;
    }

    public void setSurvey(Survey survey) {
        this.survey = survey;
    }

    public AnswerOptions getAnswerOption() {
        return answerOption;
    }

    public void setAnswerOption(AnswerOptions answerOption) {
        this.answerOption = answerOption;
    }
}
