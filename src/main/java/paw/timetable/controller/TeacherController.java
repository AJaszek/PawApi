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
import paw.timetable.model.Teacher;
import paw.timetable.repository.TeacherRepository;

/**
 *
 * @author Arek
 */
@Controller
public class TeacherController {

    @Autowired
    TeacherRepository teacherRepository;

    @GetMapping("/teacher/all")
    public @ResponseBody
    Iterable<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    @PostMapping(path = "/teacher")
    public @ResponseBody
    String addNewTeacher(@RequestBody Map<String, Object> body) {
        Teacher k = new Teacher();
        k.setFirst_name(body.get("first_name").toString());
        k.setLast_name(body.get("last_name").toString());
        k.setTitle(body.get("title").toString());

        teacherRepository.save(k);
        return "Saved";
    }
}
