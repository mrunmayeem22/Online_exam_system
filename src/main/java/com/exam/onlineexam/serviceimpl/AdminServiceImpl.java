package com.exam.onlineexam.serviceimpl;

import org.springframework.stereotype.Service;

import com.exam.onlineexam.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

    @Override
    public boolean validateAdmin(String email, String password) {
        
        return email.equals("admin@exam.com") && password.equals("admin123");
    }
}
