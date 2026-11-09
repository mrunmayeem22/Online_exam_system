package com.exam.onlineexam.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
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
@Table(name = "exams")
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long examId;

    private String subject;
    private String title;
    private int duration; 
    private int totalMarks;
    private int totalQuestions;
    private String status; 

    @ManyToOne
    @JoinColumn(name = "created_by")
    private User createdBy;

@OneToMany(mappedBy = "exam", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
private List<Question> questions;

    public Long getExamId() {
        return examId;
    }

    public void setExamId(Long examId) {
        this.examId = examId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTitle() {
        return title;
    }
 
    public void setTitle(String title) {
        this.title = title;
    }
 
    public int getDuration() {
        return duration;
    }
 
    public void setDuration(int duration) {
        this.duration = duration;
    }
 
    public int getTotalMarks() {
        return totalMarks;
    }
 
    public void setTotalMarks(int totalMarks) {
        this.totalMarks = totalMarks;
    }
 
    public int getTotalQuestions() {
        return totalQuestions;
    }
 
    public void setTotalQuestions(int totalQuestions) {
        this.totalQuestions = totalQuestions;
    }
 
    public String getStatus() {
        return status;
    }
 
    public void setStatus(String status) {
        this.status = status;
    }
 
    public User getCreatedBy() {
        return createdBy;
    }
 
    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }
    public List<Question> getQuestions() {
    return questions;
}

public void setQuestions(List<Question> questions) {
    this.questions = questions;
}

}

