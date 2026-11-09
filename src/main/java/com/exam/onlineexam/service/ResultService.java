package com.exam.onlineexam.service;

import java.util.List;
import com.exam.onlineexam.entity.Result;

public interface ResultService {

    void saveResult(Result result);

    List<Result> getResultsByStudent(Long studentId);

    List<Result> getAllResults();
}
