package com.exam.onlineexam.service;

import com.exam.onlineexam.entity.User;

public interface StudentService {

    void registerStudent(User student);

    User loginStudent(String email, String password);
}

