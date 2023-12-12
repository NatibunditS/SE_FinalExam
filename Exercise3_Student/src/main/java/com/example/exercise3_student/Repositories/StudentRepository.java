package com.example.exercise3_student.Repositories;

import com.example.exercise3_student.Entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student,Long> {
    //For searching records
    List<Student> findStudentById (long kw);

}
