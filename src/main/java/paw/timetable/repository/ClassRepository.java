/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paw.timetable.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import paw.timetable.model.Class;

/**
 *
 * @author Arek
 */
public interface ClassRepository extends JpaRepository<Class, Integer>{
    
    
    @Query(value =
            "SELECT class.id, class.name,class.teacher_id FROM class join teacher on class.teacher_id=teacher.id WHERE teacher.last_name = ?1",
            nativeQuery = true)
    List<Class> findClassByTeacherId(String lastName);


}
