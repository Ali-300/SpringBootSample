package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;
import java.util.Objects;

@Service
public class StudentService {

    private final StudentRepository studentRepository ;
@Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudent() {
        return studentRepository.findAll();
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

    public void addNewStudent(Student student)
    {
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
        if (studentOptional.isPresent())
        {
            throw new IllegalStateException("Email is already used ");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId)
    {

       boolean exists = studentRepository.existsById(studentId);
       if(!exists)
       {
           throw new IllegalStateException("Student with id " + studentId + " does not exists ");
       }
       studentRepository.deleteById(studentId);

    }
    @Transactional
    //lock database
    public void updateStudent
            (Long studentId,
             String name,
             String email)
    {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("Student with id " + studentId + " Does Not exists "));
        if (name != null &&
                name.length() > 0 &&
                !Objects.equals(student.getName(), name)) {
            student.setName(name);
        }
        if(email != null && email.length() > 0 &&
                !Objects.equals(student.getEmail(),email))
        {
            Optional <Student> studentOptional = studentRepository.findStudentByEmail(email );
            if (studentOptional.isPresent())
            {
                throw new IllegalStateException("Email is already Exist");
            }
            student.setEmail(email);
        }
        studentRepository.save(student);

    }
}

















