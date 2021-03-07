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

    @JoinColumn(name = "question_id", nullable = false)
    private Questionary questionary;
    private String answer;

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

    public Questionary getQuestionary() {
        return questionary;
    }

    public void setQuestionary(Questionary questionary) {
        this.questionary = questionary;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }


    //    private String competence;
//    private String understandableLecture;
//    private String syllabus;
//    private String homeworkCheck;
//    private String comment;
}
