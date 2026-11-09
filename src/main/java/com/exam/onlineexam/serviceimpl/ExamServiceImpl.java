package com.exam.onlineexam.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.onlineexam.entity.Exam;
import com.exam.onlineexam.repository.ExamRepository;
import com.exam.onlineexam.service.ExamService;

@Service  
public class ExamServiceImpl implements ExamService {

    @Autowired
    private ExamRepository examRepository;

    @Override
    public Exam createExam(Exam exam) {
        return examRepository.save(exam);
    }

    @Override
    public List<Exam> getActiveExams() {
        return examRepository.findByStatus("ACTIVE");
    }

    @Override
    public Exam getExamById(Long examId) {
        return examRepository.findById(examId).orElse(null);
    }
}
