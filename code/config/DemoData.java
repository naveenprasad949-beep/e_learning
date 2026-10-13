package com.learnhub.config;

import com.learnhub.entity.*;
import com.learnhub.repository.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DemoData {
  @Bean
  CommandLineRunner seed(
      CustomerRepository users,
      CourseRepository courses,
      LessonRepository lessons,
      PasswordEncoder encoder,
      @Value("${learnhub.admin-password}") String password) {
    return args -> {
      if (users.findByEmail("admin@learnhub.com").isEmpty()) {
        var u = new Customer();
        u.setName("LearnHub Administrator");
        u.setEmail("admin@learnhub.com");
        u.setRole("ADMIN");
        u.setPasswordHash(encoder.encode(password));
        users.save(u);
      }
      if (courses.count() == 0) {
        String[][] data = {
          {
            "Java & Object-Oriented Programming",
            "Programming",
            "Learn classes, objects, inheritance, encapsulation and polymorphism."
          },
          {"SQL Database Fundamentals", "Data", "Design relational schemas and write SQL queries."},
          {
            "Web Development Essentials",
            "Web Development",
            "Build accessible interfaces using HTML, CSS and JavaScript."
          }
        };
        for (var row : data) {
          var c = new Course();
          c.setTitle(row[0]);
          c.setCategory(row[1]);
          c.setDescription(row[2]);
          courses.save(c);
          var l = new Lesson();
          l.setCourseId(c.getId());
          l.setTitle("Getting started");
          l.setContent(
              row[2]
                  + " Start by writing down three learning goals. Practice each concept with a"
                  + " small example, then add your reflections to your study notes.");
          lessons.save(l);
        }
      }
    };
  }
}
