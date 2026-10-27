
package com.example.edubridge_backend.service;

import com.example.edubridge_backend.entity.Student;
import com.example.edubridge_backend.repository.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // CREATE
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    // READ ALL
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // READ ONE
    public Student getStudentById(Integer id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Student " + id + " does not exist"
                ));
    }

    // UPDATE
    public Student updateStudent(Integer id, Student details) {
        Student student = getStudentById(id);

        student.setFirstName(details.getFirstName());
        student.setLastName(details.getLastName());
        student.setEmail(details.getEmail());
        student.setSection(details.getSection());

        return studentRepository.save(student);
    }

    // DELETE
    public void deleteStudent(Integer id) {
        Student student = getStudentById(id);
        studentRepository.delete(student);
    }
}
