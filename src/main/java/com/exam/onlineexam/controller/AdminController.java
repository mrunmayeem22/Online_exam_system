package com.exam.onlineexam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.exam.onlineexam.entity.Exam;
import com.exam.onlineexam.repository.ResultRepository;
import com.exam.onlineexam.service.ExamService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ExamService examService;

    @Autowired
    private ResultRepository resultRepository;

    @GetMapping("/login")
    public String adminLogin() {
        return "admin/admin-login";
    }

@PostMapping("/login")
public String adminLoginProcess(
        @RequestParam String email,
        @RequestParam String password,
        HttpSession session,
        Model model) {

    if ("admin@exam.com".equals(email) && "admin123".equals(password)) {

        session.setAttribute("adminLoggedIn", true);   // ✅ ADD THIS
        session.setAttribute("adminId", 0L);
        session.setAttribute("adminName", "Admin");

        return "redirect:/admin/dashboard";
    }

    model.addAttribute("error", "Invalid admin credentials");
    return "admin/admin-login";
}

    @GetMapping("/dashboard")
    public String adminDashboard(HttpSession session, Model model) {

        if (session.getAttribute("adminLoggedIn") == null) {
            return "redirect:/admin/login";
        }

        model.addAttribute("exams", examService.getActiveExams());
        return "admin/admin-dashboard";
    }

    @GetMapping("/create-exam")
    public String createExamPage(HttpSession session) {

        if (session.getAttribute("adminLoggedIn") == null) {
            return "redirect:/admin/login";
        }

        return "admin/create-exam";
    }

    @PostMapping("/create-exam")
    public String createExamSubmit(
            @RequestParam String subject,
            @RequestParam String title,
            @RequestParam int duration,
            @RequestParam int totalMarks,
            @RequestParam int totalQuestions,
            HttpSession session) {

        if (session.getAttribute("adminLoggedIn") == null) {
            return "redirect:/admin/login";
        }

        Exam exam = new Exam();
        exam.setSubject(subject);
        exam.setTitle(title);
        exam.setDuration(duration);
        exam.setTotalMarks(totalMarks);
        exam.setTotalQuestions(totalQuestions);
        exam.setStatus("INACTIVE");

        Exam savedExam = examService.createExam(exam);

        session.setAttribute("examId", savedExam.getExamId());
        session.setAttribute("totalQuestions", totalQuestions);
        session.setAttribute("currentQuestion", 1);

        return "redirect:/question/add";
    }

    @GetMapping("/active-exams")
    public String activeExams(HttpSession session) {

        if (session.getAttribute("adminLoggedIn") == null) {
            return "redirect:/admin/login";
        }

        return "admin/active-exams";
    }

  @GetMapping("/results")
public String viewResults(HttpSession session, Model model) {

    if (session.getAttribute("adminLoggedIn") == null) {
        return "redirect:/admin/login";
    }
    model.addAttribute("results", resultRepository.findAll());
    return "admin/view-results";
}
}
