/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paw.timetable.controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import paw.timetable.model.Student;
import paw.timetable.model.Teacher;
import paw.timetable.repository.StudentRepository;

/**
 *
 * @author Arek
 */
@Controller
public class StudentController {

    @Autowired
    StudentRepository studentRepository;

    @GetMapping("/student/all")
    public @ResponseBody
    Iterable<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @PostMapping(path = "/student")
    public @ResponseBody
    String addNewStudent(@RequestBody Map<String, Object> body) {
        Student student = new Student();
        student.setFirst_name(body.get("first_name").toString());
        student.setLast_name(body.get("last_name").toString());
        student.setClassId((int) body.get("class_id"));

        studentRepository.save(student);
        return "Saved";
    }
}
