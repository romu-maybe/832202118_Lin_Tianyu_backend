package com.example.demo.service;

import com.example.demo.converter.StudentConverter;
import com.example.demo.dao.Student;
import com.example.demo.dao.StudentRepository;
import com.example.demo.dto.StudentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;


@Service
public class StudentServiceimpl implements StudentService{
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public StudentDTO getStudentById(long id) {
        Student student = studentRepository.findById(id).orElseThrow(RuntimeException::new);
        return StudentConverter.convertStudent(student);
    }

    @Override
    public Long addNewStudent(StudentDTO studentDTO) {
        List<Student> studentList = studentRepository.findByPhone(studentDTO.getPhone());
        if (!CollectionUtils.isEmpty(studentList)) {
            throw new IllegalArgumentException("Phone number: " + studentDTO.getPhone() + " already exists");
        }
        Student student = studentRepository.save(StudentConverter.convertStudent(studentDTO));
        return student.getId();
    }

    @Override
    public void deleteStudentById(long id) {
        studentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("id:" + id +"Student not found"));
        studentRepository.deleteById(id);
    }

    @Override
    @Transactional
    public StudentDTO updateStudentById(long id, String name,String gender) {
        System.out.println(name + gender);
        Student studentInDB = studentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("id:" + id +"Student not found"));
        System.out.println("Before Update: " + studentInDB.getName()+studentInDB.getGender());
        if(StringUtils.hasLength(name) && !studentInDB.getName().equals(name)) {
            studentInDB.setName(name);
        }

        if (StringUtils.hasLength(gender) && !studentInDB.getGender().equals(gender)) {
            studentInDB.setGender(gender);
        }

        Student student = studentRepository.save(studentInDB);
        System.out.println("after Update: " + studentInDB.getName()+studentInDB.getGender() + gender + name);

        return StudentConverter.convertStudent(student);
    }

}
