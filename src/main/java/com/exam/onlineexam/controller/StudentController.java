package com.exam.onlineexam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.exam.onlineexam.entity.User;
import com.exam.onlineexam.repository.ResultRepository;
import com.exam.onlineexam.repository.UserRepository;
import com.exam.onlineexam.service.ExamService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private ExamService examService;

    @Autowired
    private ResultRepository resultRepository;

    @Autowired
    private UserRepository userRepository;

     @GetMapping("/login")
    public String studentLogin() {
        return "student/student-login";
    }

    @PostMapping("/login")
    public String studentLoginProcess(
            @RequestParam String rollNumber,
            @RequestParam String password,
            HttpSession session,
            Model model) {

        User user = userRepository.findByRollNumber(rollNumber);

        if (user == null) {
            model.addAttribute("error", "Student not found");
            return "student/student-login";
        }

        if (!user.getPassword().equals(password)) {
            model.addAttribute("error", "Incorrect password");
            return "student/student-login";
        }

        if (!"STUDENT".equals(user.getRole())) {
            model.addAttribute("error", "Not a student account");
            return "student/student-login";
        }

        session.setAttribute("studentUserId", user.getUserId());
        session.setAttribute("studentRollNumber", user.getRollNumber());
        session.setAttribute("studentName", user.getName());

        return "redirect:/student/dashboard";
    }

    @GetMapping("/register")
    public String studentRegister() {
        return "student/student-register";
    }

    @PostMapping("/register")
    public String studentRegisterProcess(
            @RequestParam String rollNumber,
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String password,
            Model model) {

        if (userRepository.findByRollNumber(rollNumber) != null) {
            model.addAttribute("error", "Roll number already registered");
            return "student/student-register";
        }

        User user = new User();
        user.setRollNumber(rollNumber);
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);   
        user.setRole("STUDENT");

        userRepository.save(user);

        return "redirect:/student/login";
    }

       @GetMapping("/dashboard")
    public String studentDashboard(HttpSession session) {
        if (session.getAttribute("studentUserId") == null) {
            return "redirect:/student/login";
        }
        return "student/student-dashboard";
    }

  
    @GetMapping("/available-exams")
    public String availableExams(Model model) {
        model.addAttribute("exams", examService.getActiveExams());
        return "student/available-exams";
    }

    @GetMapping("/my-results")
    public String myResults(HttpSession session, Model model) {

        Long studentUserId =
                (Long) session.getAttribute("studentUserId");

        if (studentUserId == null) {
            return "redirect:/student/login";
        }

        User student =
                userRepository.findById(studentUserId).orElse(null);

       model.addAttribute(
    "results",
    resultRepository.findByStudentUserId(student.getUserId())
);

        return "student/my-results";
    }
}
