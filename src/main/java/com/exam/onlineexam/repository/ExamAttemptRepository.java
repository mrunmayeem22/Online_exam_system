package com.exam.onlineexam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.exam.onlineexam.entity.ExamAttempt;

public interface ExamAttemptRepository extends JpaRepository<ExamAttempt, Long> {
}
