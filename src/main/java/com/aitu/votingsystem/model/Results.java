package com.aitu.votingsystem.model;

import javax.persistence.*;

@Entity
@Table(name = "results")
public class Results {
    @Id
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "answer_option_id", nullable = false)
    private AnswerOptions answerOption;

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

    public AnswerOptions getAnswerOption() {
        return answerOption;
    }

    public void setAnswerOption(AnswerOptions answerOption) {
        this.answerOption = answerOption;
    }
}
