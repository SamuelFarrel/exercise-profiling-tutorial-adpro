package com.advpro.profiling.tutorial.service;

import com.advpro.profiling.tutorial.model.Student;
import com.advpro.profiling.tutorial.model.StudentCourse;
import com.advpro.profiling.tutorial.repository.StudentCourseRepository;
import com.advpro.profiling.tutorial.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author muhammad.khadafi
 */
@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentCourseRepository studentCourseRepository;

    public List<StudentCourse> getAllStudentsWithCourses() {
        List<Student> students = studentRepository.findAll();
        List<StudentCourse> allStudentCourses = studentCourseRepository.findAll();
        List<StudentCourse> studentCourses = new ArrayList<>();
        HashMap<Long,Student> studentHashMap = new HashMap<>();
        for (Student student : students) {
            studentHashMap.put(student.getId(), student);
        }
        for(StudentCourse studentCourse : allStudentCourses){
            Student studentOnCourse = studentHashMap.get(studentCourse.getStudent().getId());
            StudentCourse newStudentCourse = new StudentCourse();
            newStudentCourse.setStudent(studentOnCourse);
            newStudentCourse.setCourse(studentCourse.getCourse());
            studentCourses.add(newStudentCourse);
        }
        return studentCourses;
    }

    public Optional<Student> findStudentWithHighestGpa() {
        List<Student> students = studentRepository.findAll();
        return students.stream()
                .max(java.util.Comparator.comparingDouble(Student::getGpa));
    }

    public String joinStudentNames() {
        List<Student> students = studentRepository.findAll();

        return students.stream()
                .map(Student::getName)
                .collect(Collectors.joining(", "));
    }
}

