/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paw.timetable.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import paw.timetable.model.Pair;
import paw.timetable.model.Subject;
import paw.timetable.model.SubjectWithNames;

/**
 *
 * @author Arek
 */
public interface SubjectRepository extends JpaRepository<Subject, Integer> {

    String sql = "SELECT s.id, day_of_week, start, end,name_id, n.name as subject_name ,class_id, c.name as class_name "
            + "FROM subject s join subject_name n on s.name_id=n.id join class c on s.class_id = c.id";

    @Query(value
            = sql,
            nativeQuery = true)
    List<SubjectWithNames> findAllSubjects();

    @Query(value
            = sql + " WHERE class_id = ?1",
            nativeQuery = true)
    List<SubjectWithNames> findTimetableByClassId(int id);

    @Query(value
            = sql + " WHERE class_id in (select class_id from student where last_name = ?1) ORDER BY day_of_week, start",
            nativeQuery = true)
    List<SubjectWithNames> findTimetableByStudentName(String lastName);

    @Query(value
            = sql + " WHERE class_id in (select class_id from student where last_name = ?1) AND day_of_week = WEEKDAY(CURDATE())+1",
            nativeQuery = true)
    List<SubjectWithNames> findTimetableToday(String lastName);

    /*
    @Query(value
        = sql + " WHERE class_id in (select class_id from student where last_name = ?1) AND day_of_week = WEEKDAY(CURDATE())+1",
        nativeQuery = true)    
    public Iterable<SubjectWithNames> findSubjectNext(String lastName);
     */
    @Query(value
            = "SELECT COUNT(*) FROM subject WHERE class_id in (select class_id from student where last_name = ?1)",
            nativeQuery = true)
    public int getWeeklyHours(String lastName);
    
    @Query(value
            = "SELECT COUNT(DISTINCT name_id) FROM subject WHERE class_id in (select class_id from student where last_name = ?1)",
            nativeQuery = true)
    public int getNumberOfSubjects(String lastName);

}
