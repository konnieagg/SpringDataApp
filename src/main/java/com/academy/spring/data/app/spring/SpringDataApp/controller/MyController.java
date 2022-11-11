package com.academy.spring.data.app.spring.SpringDataApp.controller;

import com.academy.spring.data.app.spring.SpringDataApp.studentRepository.Student;
import com.academy.spring.data.app.spring.SpringDataApp.studentHtmlService.StudentHtmlServiceImpl;
import com.academy.spring.data.app.spring.SpringDataApp.studentRepository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

@Controller
public class MyController {

    private StudentRepository studentRepository;
    private StudentHtmlServiceImpl studentHtmlServiceImpl;

    @Autowired
    public MyController(StudentRepository studentRepository, StudentHtmlServiceImpl studentHtmlServiceImpl) {
        this.studentRepository = studentRepository;
        this.studentHtmlServiceImpl = studentHtmlServiceImpl;
    }

    @PostMapping("/add")
    @ResponseBody
    public String addStudent(@RequestParam String name, String lastName, int age, String occupation) {
        Student student = new Student();
        student.setName(name);
        student.setLastName(lastName);
        student.setAge(age);
        studentRepository.save(student);
        student.setOccupation(occupation);
        return studentHtmlServiceImpl.updateStudentList(studentRepository);
    }

    @GetMapping("/")
    public ResponseEntity<String> addStudent() {

        return ResponseEntity.ok(studentHtmlServiceImpl.addNewStudent());
    }
    @GetMapping("/delete")
    @ResponseBody
    public String deleteStudent(@RequestParam Long id) {

        studentRepository.deleteById(id);

        return studentHtmlServiceImpl.deleteStudent();

    }
    Student student = new Student();

    @GetMapping("/update")
    @ResponseBody
    public String updateStudent(@RequestParam Long id) {
        Optional<Student> studentOptional = studentRepository.findById(id);
        if (studentOptional.isPresent()) {
            student = studentOptional.get();

        }else {
            return "Id doesn't exist";
        }
        return studentHtmlServiceImpl.updateStudent(student);
    }

    @PostMapping("/save")
    @ResponseBody
    public String addStudent2(@RequestParam String name, String lastName, int age, String occupation) {
        student.setName(name);
        student.setLastName(lastName);
        student.setAge(age);
        student.setOccupation(occupation);
        studentRepository.save(student);
        return studentHtmlServiceImpl.updateStudentList(studentRepository);
    }
    @GetMapping("/display")
    @ResponseBody
    public String displayListStudent() {
        return studentHtmlServiceImpl.displayStudent(studentRepository);
    }

}
