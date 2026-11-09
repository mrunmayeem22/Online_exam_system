package com.exam.onlineexam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.exam.onlineexam.entity.Option;

public interface OptionRepository extends JpaRepository<Option, Long> {
}
