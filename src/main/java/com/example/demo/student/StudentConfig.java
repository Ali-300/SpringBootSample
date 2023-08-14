package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static java.time.Month.*;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository)
    {
     return args -> {
                      Student  Mariam =  new Student(

                        "Mariam",
                        "mariam@gmail.com",
                        LocalDate.of(2000, JANUARY,5)
//                              ,21
                );

                     Student  Zahra =  new Student(

                             "Zahra",
                             "Zahra@gmail.com",
                             LocalDate.of(1997, JANUARY,5)
//                             ,21
                     );

                     Student  Alex =  new Student(

                             "Alex",
                             "Alex@gmail.com",
                             LocalDate.of(2011, JANUARY,5)
//                             ,21
                     );
                     repository.saveAll(
                             List.of(Mariam,Alex)
                     );

     };
    }
}
