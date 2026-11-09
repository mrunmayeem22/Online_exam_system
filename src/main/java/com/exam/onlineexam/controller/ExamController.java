package com.exam.onlineexam.controller;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.exam.onlineexam.entity.Exam;
import com.exam.onlineexam.entity.Option;
import com.exam.onlineexam.entity.Question;
import com.exam.onlineexam.entity.Result;
import com.exam.onlineexam.entity.User;
import com.exam.onlineexam.repository.ResultRepository;
import com.exam.onlineexam.repository.UserRepository;
import com.exam.onlineexam.service.ExamService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/exam")
public class ExamController {

    @Autowired
    private ExamService examService;

    @Autowired
    private ResultRepository resultRepository;

    @Autowired
    private UserRepository userRepository;


    @GetMapping("/start/{examId}")
    public String startExam(@PathVariable Long examId, Model model) {

        Exam exam = examService.getExamById(examId);

        model.addAttribute("exam", exam);
        model.addAttribute("questions", exam.getQuestions());

        return "student/take-exam";
    }

    @PostMapping("/submit")
    @Transactional
    public String submitExam(
            @RequestParam Long examId,
            @RequestParam Map<String, String> params,
            HttpSession session,
            Model model) {

        Exam exam = examService.getExamById(examId);

        int score = 0;

        for (Question question : exam.getQuestions()) {

            String selectedOptionId =
                    params.get("q_" + question.getQuestionId());

            if (selectedOptionId == null) continue;

            for (Option option : question.getOptions()) {
                if (option.getOptionId().toString().equals(selectedOptionId)
                        && option.isCorrect()) {
                    score += question.getMarks();
                }
            }
        }

        Long studentUserId =
                (Long) session.getAttribute("studentUserId");

        if (studentUserId == null) {
            return "redirect:/student/login";
        }

        User student =
                userRepository.findById(studentUserId).orElse(null);

        if (student == null) {
            return "redirect:/student/login";
        }

        Result result = new Result();
        result.setExam(exam);
        result.setStudent(student);
        result.setScore(score);
        result.setSubmittedAt(LocalDateTime.now());

        Result savedResult = resultRepository.save(result);

        model.addAttribute("score", savedResult.getScore());
        model.addAttribute("totalMarks", exam.getTotalMarks());

        return "student/exam-result";
    }
}
