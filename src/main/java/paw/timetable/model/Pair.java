/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paw.timetable.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Arek
 */
@Entity
public class Pair {
    @Id
    int id;
    int number;

    public Pair() {
    }

    public Pair(int id, int b) {
        this.id = id;
        this.number = b;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
    
}
