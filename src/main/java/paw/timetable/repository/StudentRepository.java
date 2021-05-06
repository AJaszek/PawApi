/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paw.timetable.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import paw.timetable.model.Student;

/**
 *
 * @author Arek
 */
public interface StudentRepository extends JpaRepository<Student, Integer>{
    
}
