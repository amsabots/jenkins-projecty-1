package com.example.homework2.service;


import com.example.homework2.entity.Student;
import com.example.homework2.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(long id) {
        return studentRepository.getStudentById(id);
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

}
