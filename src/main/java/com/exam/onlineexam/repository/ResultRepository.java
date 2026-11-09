package com.exam.onlineexam.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.onlineexam.entity.Result;

public interface ResultRepository extends JpaRepository<Result, Long> {

    List<Result> findByStudentUserId(Long studentUserId);

}
