package com.exam.onlineexam.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.onlineexam.entity.User;
import com.exam.onlineexam.repository.UserRepository;
import com.exam.onlineexam.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void registerStudent(User student) {
        student.setRole("STUDENT");
        userRepository.save(student);
    }

@Override
public User loginStudent(String rollNumber, String password) {

    User user = userRepository.findByRollNumber(rollNumber);

    if (user == null) {
        return null;
    }

    if (!user.getPassword().equals(password)) {
        return null;
    }

    if (!"STUDENT".equals(user.getRole())) {
        return null;
    }
    return user;
}
}


