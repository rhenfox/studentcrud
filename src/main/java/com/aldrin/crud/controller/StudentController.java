/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aldrin.crud.controller;

import com.aldrin.crud.entity.Student;
import com.aldrin.crud.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Java Programming with Aldrin
 */
@Controller
@RequestMapping("/")
public class StudentController {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/studentList")
    public String studentList(Model model) {
        model.addAttribute("students", studentRepository.findAll());
        return "studentList";
    }

    @GetMapping("/addStudent")
    public String addStudent(Student student) {
        return "addStudent";
    }

    @PostMapping("/add")
    public String addStudent(Student student, Model model) {
        studentRepository.save(student);
        return "redirect:studentList";
    }

    @GetMapping("/updateStudent/{id}")
    public String showUpdateStudentForm(@PathVariable("id") long id, Model model) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid student id:" + id));
        model.addAttribute("student", student);
        return "updateStudent";
    }

    @PostMapping("/update/{id}")
    public String updateStudent(@PathVariable("id") long id, Student student, Model model) {
        studentRepository.save(student);
        model.addAttribute("students", studentRepository.findAll());
        return "redirect:/studentList";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable("id") long id, Model model) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid student id:" + id));
        studentRepository.delete(student);
        model.addAttribute("students", studentRepository.findAll());
        return "redirect:/studentList";
    }

}
