package paw.timetable.model;

import com.sun.istack.NotNull;
import javax.persistence.*;
//import javax.validation.constraints.NotBlank;


@Entity
@Table(name = "subject_name")
public class SubjectName {
    @Id
    @Column(name="id")
    private int id;
    //@NotNull
    @Column(name="name")
    private String name;

    public SubjectName() {
    }

    public SubjectName(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
