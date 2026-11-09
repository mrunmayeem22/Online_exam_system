package com.exam.onlineexam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.onlineexam.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

      User findByRollNumber(String rollNumber);
      User findByEmail(String email);
}
