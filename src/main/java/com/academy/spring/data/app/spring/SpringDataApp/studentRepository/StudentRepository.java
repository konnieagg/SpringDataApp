package com.academy.spring.data.app.spring.SpringDataApp.studentRepository;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StudentRepository extends CrudRepository<Student,Long> {

    List<Student> findByName(String name);

}
