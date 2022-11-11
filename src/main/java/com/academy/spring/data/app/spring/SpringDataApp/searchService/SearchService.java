package com.academy.spring.data.app.spring.SpringDataApp.searchService;

import com.academy.spring.data.app.spring.SpringDataApp.studentRepository.Student;

import java.util.List;

public interface SearchService {
    List<Student> findStudentByName(String name);
}
