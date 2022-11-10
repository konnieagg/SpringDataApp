package com.academy.spring.data.app.spring.SpringDataApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

@Controller
public class MyController {

    private StudentRepository studentRepository;

    @Autowired
    public MyController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
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
        Iterable<Student> studentList = studentRepository.findAll();
        String studentInfo = "";
        for (Student s : studentList) {

            studentInfo += String.format("<ul>" +
                    "<li> %d %s %s %d %s ", s.getId(), s.getName(), s.getLastName(), s.getAge(), s.getOccupation()) +
                    "<a href='/delete?id=" + s.getId() + "'><button>Delete student</button></a>" +
                    "<a href='/update?id=" + s.getId() + "'><button>Update student</button></a>" +
                    "</li>" +
                    "</ul>";
        }

        return studentInfo +
                "<form action=\"/\" method=\"GET\">\n" +
                "<button>Back</button>\n" +
                "</form>";
    }

    @GetMapping("/")

    public ResponseEntity<String> addStudent() {

        return ResponseEntity.ok("<form action=\"/add\" method=\"POST\">\n" +
                "<input name=\"name\" placeholder=\"Your name\">\n" +
                "<input name=\"lastName\" placeholder=\"Your lastName\">\n" +
                "<input name=\"age\" placeholder=\"Your age\">\n" +
                "<input name=\"occupation\" placeholder=\"Your occupation\">\n" +
                "<button>Go</button>\n" +
                "</form>");
    }
    @GetMapping("/delete")
    @ResponseBody
    public String deleteStudent(@RequestParam Long id) {

        studentRepository.deleteById(id);

        return "Deleted! " +
                "<form action=\"/\" method=\"GET\">\n" +
                "<button>Back to add</button>\n" +
                "</form>" +
                "<form action=\"/del\" method=\"GET\">\n" +
                "<button>Back to delete</button>\n" +
                "</form>" +
                "<form action=\"/display\" method=\"GET\">\n" +
                "<button>Back to display</button>\n" +
                "</form>";

    }
    @GetMapping("/del")

    public ResponseEntity<String> deleteStudent() {
        return ResponseEntity.ok("<form action=\"/delete\" method=\"POST\">\n" +
                "<input name=\"id\" placeholder=\"Id\">\n" +
                "<button>Delete</button>\n" +
                "</form>");
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
        return
                "<form action=\"/save\" method=\"POST\">\n"+
                        "<input name='name' value=\"" + student.getName() + "\">\n" +
                        "<input name=\"lastName\" value=\"" + student.getLastName() + "\">\n" +
                        "<input name=\"age\" value=\"" + student.getAge() + "\">\n" +
                        "<input name=\"occupation\" value=\"" + student.getOccupation() + "\">\n" +
                        "<button>Save</button>\n" +
                        "</form>";

    }

    @PostMapping("/save")
    @ResponseBody
    public String addStudent2(@RequestParam String name, String lastName, int age, String occupation) {
        student.setName(name);
        student.setLastName(lastName);
        student.setAge(age);
        student.setOccupation(occupation);
        studentRepository.save(student);
        Iterable<Student> studentList = studentRepository.findAll();
        String studentInfo = "";
        for (Student s : studentList) {

            studentInfo += String.format("<ul>" +
                    "<li> %d %s %s %d %s ", s.getId(), s.getName(), s.getLastName(), s.getAge(), s.getOccupation()) +
                    "<a href='/delete?id=" + s.getId() + "'><button>Delete student</button></a>" +
                    "<a href='/update?id=" + s.getId() + "'><button>Update student</button></a>" +
                    "</li>" +
                    "</ul>";
        }
        return studentInfo +
                "<form action=\"/\" method=\"GET\">\n" +
                "<button>Back</button>\n" +
                "</form>";
    }
    @GetMapping("/display")
    @ResponseBody
    public String displayListStudent() {
        Iterable<Student> studentList = studentRepository.findAll();
        String studentInfo = "";
        for (Student s : studentList) {

            studentInfo += String.format("<ul>" +
                    "<li> %d %s %s %d %s ", s.getId(), s.getName(), s.getLastName(), s.getAge(), s.getOccupation()) +
                    "<a href='/delete?id=" + s.getId() + "'><button>Delete student</button></a>" +
                    "<a href='/update?id=" + s.getId() + "'><button>Update student</button></a>" +
                    "</li>" +
                    "</ul>";
        }
        return studentInfo;
    }


}
