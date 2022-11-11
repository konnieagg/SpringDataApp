package com.academy.spring.data.app.spring.SpringDataApp.studentHtmlService;

import com.academy.spring.data.app.spring.SpringDataApp.studentRepository.Student;
import com.academy.spring.data.app.spring.SpringDataApp.studentRepository.StudentRepository;

public interface StudentHtmlService {

    String updateStudentList(StudentRepository studentRepository);
    String addNewStudent();
    String displayStudent(StudentRepository studentRepository);
    String updateStudent(Student student);
    String deleteStudent();
}
