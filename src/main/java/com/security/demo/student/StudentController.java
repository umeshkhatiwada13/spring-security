package com.security.demo.student;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * @author umeshkhatiwada13@infodev
 * @project demo1
 * @created 06/07/2022 - 5:36 PM
 */
@RestController
@RequestMapping("api/v1/student")
public class StudentController {

    private static final List<Student> STUDENT_LIST = Arrays.asList(
            new Student(1, "Ram"),
            new Student(2, "Shyam"),
            new Student(3, "Hari")
    );

    @GetMapping("{id}")
    public Student getStudent(@PathVariable Integer id) {
        return STUDENT_LIST.stream().filter(student ->
                id.equals(student.getId())).findFirst().orElseThrow(() -> new IllegalArgumentException("Student with Id " + id + " not found"));
    }
}
