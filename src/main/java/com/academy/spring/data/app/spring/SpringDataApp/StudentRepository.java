package com.academy.spring.data.app.spring.SpringDataApp;

import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student,Long> {

}
