/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paw.timetable.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.sql.Time;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Arek
 */

@Entity
@Table(name = "subject")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Subject {
    
    @Id
    @Column(name = "id")
    private int id;

    @Temporal(TemporalType.TIME)
    @DateTimeFormat(style = "hh:mm")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="hh:mm")
    @Column(name = "start")
    private Date start;

    @Temporal(TemporalType.TIME)
    @DateTimeFormat(style = "hh:mm")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="hh:mm")
    @Column(name = "end")
    private Date end;
    
    @Column(name = "day_of_week")
    private int dayOfWeek;
    
    @Column(name = "name_id")
    private int subjectNameId;
    
    @Column(name = "class_id")
    private int classId;

    public Subject() {
    }

    public Subject(int id, Time start, Time end, int dayOfWeek, int nameId, int classId) {
        this.id = id;
        this.start = start;
        this.end = end;
        this.dayOfWeek = dayOfWeek;
        this.subjectNameId = nameId;
        this.classId = classId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Time start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Time end) {
        this.end = end;
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public int getSubjectNameId() {
        return subjectNameId;
    }

    public void setSubjectNameId(int subjectNameId) {
        this.subjectNameId = subjectNameId;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }
    
    
    
    
    
}
