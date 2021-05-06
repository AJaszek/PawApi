/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paw.timetable.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import paw.timetable.model.Pair;
import paw.timetable.repository.PairRepository;
import paw.timetable.repository.SubjectRepository;

/**
 *
 * @author Arek
 */
@Controller
public class StatsController {

    @Autowired
    SubjectRepository subjectRepository;
    @Autowired
    PairRepository pairRepository;

    @GetMapping("/stats/hoursWeekly/{lastName}")
    public @ResponseBody
    int getHoursWeekly(@PathVariable String lastName) {
        return subjectRepository.getWeeklyHours(lastName);

    }

    @GetMapping("/stats/hoursDaily/{lastName}")
    public @ResponseBody
    Iterable<Pair> getHoursDaily(@PathVariable String lastName) {
        return pairRepository.getDailyHours(lastName);

    }

    @GetMapping("/stats/numberOfSubjects/{lastName}")
    public @ResponseBody
    int getNumberOfSubjects(@PathVariable String lastName) {
        return subjectRepository.getNumberOfSubjects(lastName);

    }

}
