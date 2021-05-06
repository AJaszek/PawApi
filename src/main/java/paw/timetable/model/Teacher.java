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
@Table(name = "teacher")
public class Teacher extends Person{
    
    @Column(name = "title")
    private String title;
    

    public Teacher(String title, int id, String first_name, String last_name) {
        super(id, first_name, last_name);
        this.title = title;
    }

    public Teacher() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    
}
