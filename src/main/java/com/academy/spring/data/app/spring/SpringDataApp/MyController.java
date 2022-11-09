package com.academy.spring.data.app.spring.SpringDataApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyController {

    private StudentRepository studentRepository;

    @Autowired
    public MyController (StudentRepository studentRepository) {
        this.studentRepository=studentRepository;
    }

    @PostMapping("/add")
    @ResponseBody
    public String addStudent (@RequestParam String name , String lastName, int age, String occupation) {
        Student student = new Student();
        student.setName(name);
        student.setLastName(lastName);
        student.setAge(age);
        student.setOccupation(occupation);
        studentRepository.save(student);

        return "Saved! " +
                "<form action=\"/\" method=\"GET\">\n" +
                "<button>Back</button>\n" +
                "</form>";
    }

    @GetMapping ("/")

    public ResponseEntity<String> addStudent () {

        return ResponseEntity.ok("<form action=\"/add\" method=\"POST\">\n" +
                "<input name=\"name\" placeholder=\"Your name\">\n" +
                "<input name=\"lastName\" placeholder=\"Your lastName\">\n" +
                "<input name=\"age\" placeholder=\"Your age\">\n" +
                "<input name=\"occupation\" placeholder=\"Your occupation\">\n" +
                "<button>Go</button>\n" +
                "</form>");
    }


}
