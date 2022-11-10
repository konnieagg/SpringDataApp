package com.academy.spring.data.app.spring.SpringDataApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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

        Iterable<Student> studentList=studentRepository.findAll();
        String studentInfo="";
        for (Student s:studentList) {

            studentInfo+=String.format("<ul>" +
                    "<li> %d %s %s %d %s " ,s.getId(),s.getName(),s.getLastName(),s.getAge(),s.getOccupation()) +
                    "<a href='/delete?id=" + s.getId() + "'><button>Delete student</button></a>" +
                    "<a href='/update?id=" + s.getId() + "&name=" + s.getName() +"&lastName=" + s.getLastName() +
                    "&age=" + s.getAge() + "&occupation=" + s.getOccupation() +"'><button>Update student</button></a>" +
                    "</li>" +
                    "</ul>";

        }

        return  studentInfo +
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

    @GetMapping("/delete")
    @ResponseBody
    public String deleteStudent (@RequestParam Long id) {

        studentRepository.deleteById(id);

        return "Deleted! " +
                "<form action=\"/\" method=\"GET\">\n" +
                "<button>Back to add</button>\n" +
                "</form>" +
                "<form action=\"/del\" method=\"GET\">\n" +
                "<button>Back to delete</button>\n" +
                "</form>";

    }
    @GetMapping ("/del")

    public ResponseEntity<String> deleteStudent () {
        return ResponseEntity.ok("<form action=\"/delete\" method=\"POST\">\n" +
                "<input name=\"id\" placeholder=\"Id\">\n" +
                "<button>Delete</button>\n" +
                "</form>");
    }

    @PostMapping("/save")
    @ResponseBody
    public String saveUpdatedStudent (@RequestParam Long id, String name , String lastName, int age, String occupation) {
        studentRepository.deleteById(id);
        Student updateStudent=new Student();
        updateStudent.setId(id);
        updateStudent.setName(name);
        updateStudent.setLastName(lastName);
        updateStudent.setAge(age);
        updateStudent.setOccupation(occupation);

        studentRepository.save(updateStudent);

        Iterable<Student> updateStudentList=studentRepository.findAll();
        String updateStudentInfo="";
        for (Student s:updateStudentList) {

            updateStudentInfo+=String.format("<ul>" +
                    "<li> %d %s %s %d %s " ,s.getId(),s.getName(),s.getLastName(),s.getAge(),s.getOccupation()) +
                    "<a href='/delete?id=" + s.getId() + "'><button>Delete student</button></a>" +
                    "<a href='/update?id=" + s.getId() + "&name=" + s.getName() +"&lastName=" + s.getLastName() +
                    "&age=" + s.getAge() + "&occupation=" + s.getOccupation() +"'><button>Update student</button></a>" +
                    "</li>" +
                    "</ul>";

        }


        return "Updated Student with " + id;

    }


    @GetMapping ("/update")
    @ResponseBody
    public String updateStudent (@RequestParam Long id, String name , String lastName, int age, String occupation) {

        studentRepository.findById(id);


        return
                "<form action='/save?id=" + id + "&name=" + name +"&lastName=" + lastName +
                 "&age=" + age + "&occupation=" + occupation + "' method=\"POST\">\n" +
                "<input name=\"name\" placeholder=\""+name+"\">\n" +
                "<input name=\"lastName\" placeholder=\""+lastName+"\">\n" +
                "<input name=\"age\" placeholder=\""+age+"\">\n" +
                "<input name=\"occupation\" placeholder=\""+occupation+"\">\n" +
                "<button>Save</button>\n" +
                "</form>";

            }
//            "<a href='/save?id=" + id + "&name=" + name +"&lastName=" + lastName +
//            "&age=" + age + "&occupation=" + occupation + "'>




}
