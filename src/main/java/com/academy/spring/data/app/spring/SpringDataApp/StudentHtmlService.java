package com.academy.spring.data.app.spring.SpringDataApp;

public interface StudentHtmlService {

    String updateStudentList(StudentRepository studentRepository);
    String addNewStudent();
    String displayStudent(StudentRepository studentRepository);
    String updateStudent(Student student);
    String deleteStudent();
}
