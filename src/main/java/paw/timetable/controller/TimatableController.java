/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paw.timetable.controller;

import java.sql.Time;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import paw.timetable.model.Student;
import paw.timetable.model.Subject;
import paw.timetable.model.SubjectName;
import paw.timetable.model.SubjectWithNames;
import paw.timetable.model.Teacher;
import paw.timetable.repository.ClassRepository;
import paw.timetable.repository.StudentRepository;
import paw.timetable.repository.SubjectNameRepository;
import paw.timetable.repository.SubjectRepository;
import paw.timetable.repository.TeacherRepository;

//@RestController
@Controller
public class TimatableController {

   /* @Autowired
    SubjectNameRepository subjectNameRepository;
    @Autowired
    ClassRepository classRepository;
    @Autowired
    StudentRepository studentRepository;*/
    @Autowired
    SubjectRepository subjectRepository;

    @GetMapping("/timetable/class/{id}")
    public @ResponseBody
    Iterable<SubjectWithNames> getTimetableByClassId(@PathVariable int id) {
        return subjectRepository.findTimetableByClassId(id);

    }

    @GetMapping("/timetable/student/{lastName}")
    public @ResponseBody
    Iterable<SubjectWithNames> getTimetableByStudentName(@PathVariable String lastName) {
        return subjectRepository.findTimetableByStudentName(lastName);

    }

    @GetMapping("/timetable/student/{lastName}/getToday")
    public @ResponseBody
    Iterable<SubjectWithNames> getTimetableToday(@PathVariable String lastName) {
        return subjectRepository.findTimetableToday(lastName);

    }

    @GetMapping("/timetable/student/{lastName}/getNext")
    public @ResponseBody
    SubjectWithNames getTimetableNext(@PathVariable String lastName) {
        List<SubjectWithNames> timetable = subjectRepository.findTimetableByStudentName(lastName);

        Calendar todayCalendar = Calendar.getInstance();
        int today = todayCalendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (today == 0)
            today = 7;

        for (SubjectWithNames s : timetable) {
            if (s.getDayOfWeek() == today) {
                LocalTime start = LocalTime.of(s.getStart().getHours(), s.getStart().getMinutes());
                if (start.isAfter(LocalTime.now()))
                    return s;
            }
            else if (s.getDayOfWeek() > today)
                return s;
        }
        return null;
    }

    //######################################################        teacher
    /*
    @PostMapping("/subject/subjectName")
    public SubjectName createNote(@RequestBody SubjectName subjectName) {
        return subjectNameRepository.save(subjectName);
    }

    @GetMapping("/subject/subjectName/all")
    public @ResponseBody
    Iterable<SubjectName> getAllNotes() {
        return subjectNameRepository.findAll();
    }
     */
 /*


    @GetMapping("/student/all")
    public @ResponseBody
    Iterable<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @GetMapping("/subject/all")
    public @ResponseBody
    Iterable<Subject> getAllSubject() {
        return subjectRepository.findAll();
    }*/
 /*
    @GetMapping(path = "/dzial/all")
    public @ResponseBody
    Iterable<Dzial> getDzialy() {
        return dzialRepository.findAll();
    }*/
}
