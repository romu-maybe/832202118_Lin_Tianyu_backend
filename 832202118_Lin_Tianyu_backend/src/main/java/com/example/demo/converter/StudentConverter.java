package com.example.demo.converter;

import com.example.demo.dao.Student;
import com.example.demo.dto.StudentDTO;

public class StudentConverter {
    public static StudentDTO convertStudent(Student student) {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(student.getId());
        studentDTO.setName(student.getName());
        studentDTO.setGender(student.getGender());
        studentDTO.setPhone(student.getPhone());
        return studentDTO;
    }

    public static Student convertStudent(StudentDTO studentDTO) {
        Student student = new Student();
        student.setName(studentDTO.getName());
        student.setGender(studentDTO.getGender());
        student.setPhone(studentDTO.getPhone());
        student.setAge(studentDTO.getAge());
        return student;
    }
}
