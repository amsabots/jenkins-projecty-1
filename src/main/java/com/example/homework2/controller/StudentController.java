package com.example.homework2.controller;

import com.example.homework2.entity.Student;
import com.example.homework2.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    static final Logger logger = LoggerFactory.getLogger(StudentController.class.getName());
    @Autowired
    private StudentService studentService;


    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @PostMapping
    public ResponseEntity<Object> getStudent(@RequestBody Student student) {
        if (student.getParentsName() == null || student.getEmail() == null) return ResponseEntity.status(400)
                .body("Parent email and student Email are required parameters");
        try {
            return ResponseEntity.status(200).body(studentService.createStudent(student));
        } catch (Exception exception) {
            logger.error(exception.getMessage());
            return ResponseEntity.badRequest().body("Bad request, Recheck the payload parameters and try again");
        }
    }

    @GetMapping("/{id}")
    public Student getStudent(@PathVariable long id) {
        return studentService.getStudentById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateStudent(@RequestBody Student student, @PathVariable long id) {
        if (studentService.getStudentById(id) == null)
            return ResponseEntity.badRequest().body("{\"message\":\"Please provide a valid student id\"}");
        Student existingStudent = studentService.getStudentById(id);
        if ((Integer) student.getBirthYear() != null) existingStudent.setBirthYear(student.getBirthYear());
        if (student.getEmail() != null) existingStudent.setEmail(student.getEmail());
        if (student.getName() != null) existingStudent.setName(student.getName());
        if (student.getParentsName() != null) existingStudent.setParentsName(student.getParentsName());
        if (student.getStatus() != null) existingStudent.setStatus(student.getStatus());
        if (student.getGroup() != null) existingStudent.setGroup(student.getGroup());
         studentService.createStudent(existingStudent);
        return ResponseEntity.ok("{\"message\":\"The student object has been updated successfully, Fetch the student by id or reload all students\"}");

    }
}
