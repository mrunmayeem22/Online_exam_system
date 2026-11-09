package com.exam.onlineexam.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.onlineexam.entity.Result;
import com.exam.onlineexam.repository.ResultRepository;
import com.exam.onlineexam.service.ResultService;

@Service
public class ResultServiceImpl implements ResultService {

    @Autowired
    private ResultRepository resultRepository;

    @Override
    public void saveResult(Result result) {
        resultRepository.save(result);
    }
    @Override
 public List<Result> getResultsByStudent(Long userId) {
    return resultRepository.findByStudentUserId(userId);
}
    @Override
    public List<Result> getAllResults() {
        return resultRepository.findAll();
    }
}
