package com.academy.spring.data.app.spring.SpringDataApp.searchService;

import com.academy.spring.data.app.spring.SpringDataApp.studentRepository.Student;
import com.academy.spring.data.app.spring.SpringDataApp.studentRepository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchServiceImpl implements SearchService {
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<Student> findStudentByName(String name) {

        return studentRepository.findByName(name) ;
    }
}
