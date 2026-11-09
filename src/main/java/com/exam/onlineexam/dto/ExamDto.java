package com.exam.onlineexam.dto;

public class ExamDto {

    private String subject;
    private String title;
    private int duration;
    private int totalMarks;
    private int totalQuestions;

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
}
