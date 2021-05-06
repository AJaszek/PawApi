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
import paw.timetable.model.SubjectName;
import paw.timetable.repository.StudentRepository;
import paw.timetable.repository.SubjectNameRepository;

/**
 *
 * @author Arek
 */
@Controller
public class SubjectNameController {
    
        @Autowired
    SubjectNameRepository subjectNameRepository;

    @GetMapping("/subject/subjectName/all")
    public @ResponseBody
    Iterable<SubjectName> getAllNames() {
        return subjectNameRepository.findAll();
    }

    @PostMapping(path = "/subject/subjectName")
    public @ResponseBody
    String addNewSubjectName(@RequestBody Map<String, Object> body) {
        SubjectName sName = new SubjectName();
        sName.setName(body.get("name").toString());

        subjectNameRepository.save(sName);
        return "Saved";
    }
    
}
