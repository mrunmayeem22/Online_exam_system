package com.exam.onlineexam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.exam.onlineexam.entity.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}

