package com.example.demo.student;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@RestController()
@RequestMapping(path = "api/v1/student")
public class StudentController {

    private final StudentService studentService ;
    public StudentController (StudentService studentService)
    {
        this.studentService = studentService ;
    }
    @GetMapping
    public List<Student> getStudent() {
        return studentService.getStudent();
//        return List.of(
//                new Student(
//                        1L,
//                        "Mariam",
//                        "mariam@gmail.com",
//                        LocalDate.of(2000, Month.JANUARY,5),
//                        21
//                )
//        );
      }

      @PostMapping
      public void registerNewStudent(@RequestBody Student student)
      {
          studentService.addNewStudent(student);
      }
      @DeleteMapping(path = "{studentId}")
      public void deleteStudent(@PathVariable("studentId") Long studentId)
      {
          studentService.deleteStudent(studentId);
      }
      @PutMapping(path = "{studentId}")
      public void updateStudent
              (
                      @PathVariable("studentId") Long studentId,
                      @RequestParam(required = false) String name,
                      @RequestParam(required = false) String email
              )
      {
          studentService.updateStudent(studentId, name, email);
      }
}
