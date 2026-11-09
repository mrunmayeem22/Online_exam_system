package com.exam.onlineexam.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "questions")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;

   @ManyToOne
@JoinColumn(name = "exam_id", nullable = false)
private Exam exam;


    @Column(columnDefinition = "TEXT")
    private String questionText;

    private int marks;

  @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
private List<Option> options;

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Exam getExam() {
        return exam;
    }
 
    public void setExam(Exam exam) {
        this.exam = exam;
    }
 
    public String getQuestionText() {
        return questionText;
    }
 
    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }
 
    public int getMarks() {
        return marks;
    }
 
    public void setMarks(int marks) {
        this.marks = marks;
    }
public List<Option> getOptions() {
    return options;
}

public void setOptions(List<Option> options) {
    this.options = options;
}
}

