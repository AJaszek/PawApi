/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paw.timetable.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import paw.timetable.model.Pair;

/**
 *
 * @author Arek
 */
public interface PairRepository extends JpaRepository<paw.timetable.model.Pair, Integer>{
        @Query(value
            = "SELECT day_of_week as id, COUNT(*) as number FROM subject WHERE class_id in (select class_id from student where last_name = ?1) GROUP BY day_of_week",
            nativeQuery = true)
    public List<Pair> getDailyHours(String lastName);
}
