/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paw.timetable.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author Arek
 */
@Entity
@Table(name = "student")
public class Student  extends Person{
    
    @Column(name = "class_id")
    private int classId;

    public Student() {
    }
    
    public Student(int classId, int id, String first_name, String last_name) {
        super(id, first_name, last_name);
        this.classId = classId;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }
    
    
    
}
