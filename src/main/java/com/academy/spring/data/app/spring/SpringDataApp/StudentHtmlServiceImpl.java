package com.academy.spring.data.app.spring.SpringDataApp;

import org.springframework.stereotype.Service;

@Service
public class StudentHtmlServiceImpl implements StudentHtmlService {
    @Override
    public String updateStudentList(StudentRepository studentRepository) {
            return displayStudent(studentRepository) +
            "<form action=\"/\" method=\"GET\">\n" +
            "<button>Back</button>\n" +
            "</form>";
    }
    @Override
    public String addNewStudent() {
       return "<form action=\"/add\" method=\"POST\">\n" +
               "<input name=\"name\" placeholder=\"Your name\">\n" +
               "<input name=\"lastName\" placeholder=\"Your lastName\">\n" +
               "<input name=\"age\" placeholder=\"Your age\">\n" +
               "<input name=\"occupation\" placeholder=\"Your occupation\">\n" +
               "<button>Go</button>\n" +
               "</form>";
    }
    @Override
    public String displayStudent(StudentRepository studentRepository) {
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
    @Override
    public String updateStudent(Student student) {
        return
                "<form action=\"/save\" method=\"POST\">\n"+
                        "<input name='name' value=\"" + student.getName() + "\">\n" +
                        "<input name=\"lastName\" value=\"" + student.getLastName() + "\">\n" +
                        "<input name=\"age\" value=\"" + student.getAge() + "\">\n" +
                        "<input name=\"occupation\" value=\"" + student.getOccupation() + "\">\n" +
                        "<button>Save</button>\n" +
                        "</form>";

    }
    @Override
    public String deleteStudent() {
        return "Deleted! " +
                "<form action=\"/\" method=\"GET\">\n" +
                "<button>Back to add</button>\n" +
                "</form>" +
                "<form action=\"/display\" method=\"GET\">\n" +
                "<button>Back to display</button>\n" +
                "</form>";

    }


}
