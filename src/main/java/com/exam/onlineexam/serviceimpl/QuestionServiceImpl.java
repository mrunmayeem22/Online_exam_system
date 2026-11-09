package com.exam.onlineexam.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.onlineexam.entity.Question;
import com.exam.onlineexam.repository.QuestionRepository;
import com.exam.onlineexam.service.QuestionService;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public void saveQuestion(Question question) {
        questionRepository.save(question);
    }
}
