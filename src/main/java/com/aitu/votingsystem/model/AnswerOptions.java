package com.aitu.votingsystem.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "answer_options")
public class AnswerOptions {
    @Id
    @Column(name = "answer_option_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "option")
    private String option;
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "question_id")
    private Questionary questionary;

    @Column(name = "percentage")
    private double percentage;

    @OneToMany(mappedBy = "answerOption", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Results> results = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public Questionary getQuestionary() {
        return questionary;
    }

    public void setQuestionary(Questionary questionary) {
        this.questionary = questionary;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public List<Results> getResults() {
        return results;
    }
}
