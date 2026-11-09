package com.exam.onlineexam.service;

import java.util.List;

import com.exam.onlineexam.entity.Exam;

public interface ExamService {

    Exam createExam(Exam exam);

    List<Exam> getActiveExams();

    Exam getExamById(Long examId);
}
