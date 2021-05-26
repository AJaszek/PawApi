/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paw.timetable.repository;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import paw.timetable.model.SubjectWithNames;
import paw.timetable.model.User;
import static paw.timetable.repository.SubjectRepository.sql;

/**
 *
 * @author Arek
 */
public interface UserRepository extends JpaRepository<User, String>{
    
    
    /*@Transactional
public void insertWithQuery(User user) {
    entityManager.createNativeQuery("INSERT INTO UsersPaw (login, password, enabled) VALUES (?,?,1)")
      .setParameter(1, user.getLogin())
      .setParameter(2, user.getPassword())
      .executeUpdate();
}*/
    
     @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "insert into UsersPaw (login, password, enabled) "
            + "VALUES (:#{#user.login},:#{#user.password},  1 );"
            , nativeQuery = true)
    public int register(@Param("user") User user);
    
     
     @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = 
            "insert into UserRolesPaw (login, role) "
            + "VALUES ( :#{#user.login}, :#{#user.role} );"
            , nativeQuery = true)
    public int registerRole(@Param("user") User user);   
}
