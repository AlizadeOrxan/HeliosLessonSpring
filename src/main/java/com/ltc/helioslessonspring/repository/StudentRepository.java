package com.ltc.helioslessonspring.repository;

import com.ltc.helioslessonspring.model.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Long> {

    @Query("SELECT s FROM StudentEntity s WHERE s.course.id = :courseId")
    List<StudentEntity> findStudentsByCourseId(@Param("courseId") Long courseId);


//    @Query("SELECT s FROM StudentEntity s WHERE s.course.name = :courseName")
//    List<StudentEntity> findStudentsByCourseName(@Param("courseName") String courseName);

}
