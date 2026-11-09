package com.exam.onlineexam.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.exam.onlineexam.entity.Exam;
import com.exam.onlineexam.entity.Option;
import com.exam.onlineexam.entity.Question;
import com.exam.onlineexam.service.ExamService;
import com.exam.onlineexam.service.QuestionService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private ExamService examService;

  @GetMapping("/add")
public String showQuestionPage(Model model, HttpSession session) {

    Integer currentQuestion = (Integer) session.getAttribute("currentQuestion");
    Integer totalQuestions = (Integer) session.getAttribute("totalQuestions");

    if (currentQuestion == null || totalQuestions == null) {
        return "redirect:/admin/dashboard";
    }

    model.addAttribute("currentQuestion", currentQuestion);
    model.addAttribute("totalQuestions", totalQuestions);

    return "admin/add-question";
}

@PostMapping("/add")
public String saveQuestion(
        @RequestParam String questionText,
        @RequestParam String optionA,
        @RequestParam String optionB,
        @RequestParam String optionC,
        @RequestParam String optionD,
        @RequestParam String correctOption,
        @RequestParam int marks,
        HttpSession session) {

    Long examId = (Long) session.getAttribute("examId");
    Integer currentQuestion = (Integer) session.getAttribute("currentQuestion");
    Integer totalQuestions = (Integer) session.getAttribute("totalQuestions");

    Exam exam = examService.getExamById(examId);

    Question question = new Question();
    question.setExam(exam);  
    question.setQuestionText(questionText);
    question.setMarks(marks);

    List<Option> options = new ArrayList<>();

    options.add(createOption(optionA, correctOption.equals("A"), question));
    options.add(createOption(optionB, correctOption.equals("B"), question));
    options.add(createOption(optionC, correctOption.equals("C"), question));
    options.add(createOption(optionD, correctOption.equals("D"), question));

    question.setOptions(options);

    questionService.saveQuestion(question);

    if (currentQuestion >= totalQuestions) {
        exam.setStatus("ACTIVE");
        examService.createExam(exam);
        return "redirect:/admin/dashboard";
    }

    session.setAttribute("currentQuestion", currentQuestion + 1);
    return "redirect:/question/add";
}
    private Option createOption(String text, boolean isCorrect, Question question) {
        Option option = new Option();
        option.setOptionText(text);
        option.setCorrect(isCorrect);
        option.setQuestion(question);
        return option;
    }
}
