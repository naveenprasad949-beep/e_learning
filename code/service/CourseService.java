package com.learnhub.service;

import com.learnhub.dto.*;
import com.learnhub.entity.*;
import com.learnhub.repository.*;
import com.learnhub.security.CurrentUser;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@Transactional
public class CourseService implements CrudService<CourseDto> {
  private final CourseRepository repository;
  private final CurrentUser current;
  private final CourseRepository courses;
  private final PasswordEncoder encoder;

  public CourseService(
      CourseRepository repository,
      CurrentUser current,
      CourseRepository courses,
      PasswordEncoder encoder) {
    this.repository = repository;
    this.current = current;
    this.courses = courses;
    this.encoder = encoder;
  }

  private Course find(Long id) {
    var e =
        repository
            .findById(id)
            .orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found"));
    return e;
  }

  public List<CourseDto> list() {
    return repository.findAll().stream().map(this::toDto).toList();
  }

  public CourseDto get(Long id) {
    return toDto(find(id));
  }

  public CourseDto create(CourseDto d) {
    var e = new Course();
    apply(e, d);
    return toDto(repository.saveAndFlush(e));
  }

  public CourseDto update(Long id, CourseDto d) {
    var e = find(id);
    apply(e, d);
    return toDto(repository.saveAndFlush(e));
  }

  public void delete(Long id) {
    var e = find(id);
    repository.delete(e);
    repository.flush();
  }

  private void apply(Course e, CourseDto d) {
    e.setTitle(d.title());
    e.setCategory(d.category());
    e.setDescription(d.description());
  }

  private CourseDto toDto(Course e) {
    return new CourseDto(e.getId(), e.getTitle(), e.getCategory(), e.getDescription());
  }
}
