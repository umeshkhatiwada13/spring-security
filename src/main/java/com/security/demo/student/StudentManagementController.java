package com.security.demo.student;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("management/api/v1/student")
public class StudentManagementController {
    private static final List<Student> STUDENT_LIST = Arrays.asList(
            new Student(1, "Ram"),
            new Student(2, "Shyam"),
            new Student(3, "Hari")
    );

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ADMIN_TRAINEE')")
    public List<Student> getStudents() {
        return STUDENT_LIST;
    }

    @PostMapping("/save")
    @PreAuthorize("hasAuthority('student:write')")
    public void saveStudents(@RequestBody Student student) {
        System.out.println(student);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('student:write')")
    public void deleteStudent(@PathVariable("id") Integer studentId) {
        System.out.println("Student Id " + studentId);
    }

    @PutMapping("/update")
    @PreAuthorize("hasAuthority('student:write')")
    public void updateStudent(@RequestBody Student student) {
        System.out.println("Student is " + student.getId());
    }
}
