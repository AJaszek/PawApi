package paw.timetable.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import paw.timetable.model.SubjectName;


public interface SubjectNameRepository extends JpaRepository<SubjectName, Integer>{
    

    
}
