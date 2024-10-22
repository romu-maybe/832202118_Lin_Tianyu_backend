package com.example.demo.controller;

import com.example.demo.dao.Student;
import com.example.demo.dto.StudentDTO;
import com.example.demo.service.Response;
import com.example.demo.service.StudentService;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/student/{id}")
    public Response<StudentDTO> getStudentId(@PathVariable long id) {
        return Response.newSuccess(studentService.getStudentById(id));
    }

    @PostMapping("/student")
    public Response<Long> addNewStudent(@RequestBody StudentDTO studentDTO) {
    return Response.newSuccess(studentService.addNewStudent(studentDTO));
    }

    @DeleteMapping("/student/{id}")
    public void deleteStudentById(@PathVariable long id) {
        studentService.deleteStudentById(id);
    }
    @PutMapping("/student/{id}")
    public Response<StudentDTO> updateStudentById(@PathVariable long id, @RequestParam("name") String name,@RequestParam("gender") String gender) {
        return Response.newSuccess(studentService.updateStudentById(id, name, gender));
    }
}
