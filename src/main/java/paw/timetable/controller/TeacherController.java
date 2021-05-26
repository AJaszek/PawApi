/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paw.timetable.controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import paw.timetable.model.Teacher;
import paw.timetable.model.User;
import paw.timetable.repository.TeacherRepository;
import paw.timetable.repository.UserRepository;

/**
 *
 * @author Arek
 */
@Controller
public class TeacherController {

    @Autowired
    TeacherRepository teacherRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

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

    @PostMapping(path = "/register")
    public @ResponseBody
    String registerUser(@RequestBody Map<String, Object> body) {
        User u = new User();
        u.setLogin(body.get("login").toString());
        String encodedPassword = passwordEncoder.encode(body.get("password").toString());
        u.setPassword(encodedPassword);
        u.setRole("ROLE_" + body.get("role").toString());

        if (userRepository.register(u) == 1);
        userRepository.registerRole(u);

        return "Saved";
    }
}
