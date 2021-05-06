/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paw.timetable.controller;

import java.sql.Time;
import java.util.Date;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import paw.timetable.model.Subject;
import paw.timetable.model.SubjectWithNames;
import paw.timetable.repository.SubjectRepository;

/**
 *
 * @author Arek
 */
@Controller
public class SubjectController {

    @Autowired
    SubjectRepository subjectRepository;

    @GetMapping("/subject/all")
    public @ResponseBody
    Iterable<SubjectWithNames> getAllSubjects() {
        return subjectRepository.findAllSubjects();
    }

    @PostMapping(path = "/subject")
    public @ResponseBody
    String addNewSubject(@RequestBody Map<String, Object> body) {
        Subject subject = new Subject();
        subject.setDayOfWeek((int) body.get("day_of_week"));
        subject.setStart(Time.valueOf(body.get("start").toString()+":00"));
        subject.setEnd(Time.valueOf(body.get("end").toString()+":00"));
        subject.setClassId((int) body.get("class_id"));
        subject.setSubjectNameId((int) body.get("subject_name_id"));

        subjectRepository.save(subject);
        return "Saved";
    }

}
