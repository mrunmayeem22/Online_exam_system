package com.exam.onlineexam.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.exam.onlineexam.entity.Exam;

public interface ExamRepository extends JpaRepository<Exam, Long> {

    List<Exam> findByStatus(String status);
}

