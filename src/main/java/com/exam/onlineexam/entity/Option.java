package com.exam.onlineexam.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "options")
public class Option {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long optionId;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    private String optionText;

    private boolean correct;

    public Long getOptionId() {
        return optionId;
    }

    public void setOptionId(Long optionId) {
        this.optionId = optionId;
    }

    public Question getQuestion() {
        return question;
    }
 
    public void setQuestion(Question question) {
        this.question = question;
    }
 
    public String getOptionText() {
        return optionText;
    }
 
    public void setOptionText(String optionText) {
        this.optionText = optionText;
    }
 
     public boolean isCorrect() {  
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }
}

