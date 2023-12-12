package com.example.exercise3_student;

import com.example.exercise3_student.Entities.Student;
import com.example.exercise3_student.Repositories.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class Exercise3StudentApplication {

    public static void main(String[] args) {
        SpringApplication.run(Exercise3StudentApplication.class, args);
    }

    //Initialize 4 records (objects)
//    @Bean
//    CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
//        return args -> {
//            studentRepository.save(new Student(null, "Jam", new Date(), false, 1.2));
//            studentRepository.save(new Student(null, "Jen", new Date(), true, 3.2));
//            studentRepository.save(new Student(null, "Jok", new Date(), false, 1.0));
//            studentRepository.save(new Student(null, "Jos", new Date(), false, 4.2));
//            studentRepository.findAll().forEach(p -> {
//                System.out.println(p.getName());
//            });
//        };
//    }
}
