/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paw.timetable.model;

import java.sql.Time;
import javax.persistence.Entity;

/**
 *
 * @author Arek
 */
@Entity
public class SubjectWithNames extends Subject{
    
    private String className;
    private String subjectName;

    public SubjectWithNames() {
    }

    public SubjectWithNames(String className, String subjectName, int id, Time start, Time end, int dayOfWeek, int nameId, int classId) {
        super(id, start, end, dayOfWeek, nameId, classId);
        this.className = className;
        this.subjectName = subjectName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }
    
    
    
}
