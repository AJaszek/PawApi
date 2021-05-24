/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paw.timetable.controller;

import java.security.Principal;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import paw.timetable.model.Class;
import paw.timetable.repository.ClassRepository;

/**
 *
 * @author Arek
 */
@Controller
public class ClassController {

    @Autowired
    ClassRepository classRepository;

    @GetMapping("/me")
  public @ResponseBody String index( Principal principal) {
    //model.addAttribute("message", "You are logged in as " + principal.getName());
    return principal.toString();
  }
    /*@GetMapping("/stats/numberOfSubjects/{lastName}")
    public @ResponseBody
    int getNumberOfSubjects(@PathVariable String lastName) {
        return subjectRepository.getNumberOfSubjects(lastName);

    }*/
    
    @GetMapping("/class/all")
    public @ResponseBody
    Iterable<paw.timetable.model.Class> getAllClasses() {
        return classRepository.findAll();
        
    }
    @GetMapping("/class/teacher/{lastName}")
    public @ResponseBody
    Iterable<paw.timetable.model.Class> getAlClasses(@PathVariable String lastName) {
        return classRepository.findClassByTeacherId(lastName);
        
    }
    
    @PostMapping(path = "/class")
    public @ResponseBody
    String addNewClass(@RequestBody Map<String, Object> body) {
        Class classs = new Class();
        classs.setName(body.get("name").toString());
       // classs.setTeacherId((int)body.get("teacher_id"));

        classRepository.save(classs);
        return "Saved";
    }

}
