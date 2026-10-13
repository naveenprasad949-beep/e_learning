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
public class LessonService implements CrudService<LessonDto> {
  private final LessonRepository repository;
  private final CurrentUser current;
  private final CourseRepository courses;
  private final PasswordEncoder encoder;

  public LessonService(
      LessonRepository repository,
      CurrentUser current,
      CourseRepository courses,
      PasswordEncoder encoder) {
    this.repository = repository;
    this.current = current;
    this.courses = courses;
    this.encoder = encoder;
  }

  private Lesson find(Long id) {
    var e =
        repository
            .findById(id)
            .orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Lesson not found"));
    return e;
  }

  public List<LessonDto> list() {
    return repository.findAll().stream().map(this::toDto).toList();
  }

  public LessonDto get(Long id) {
    return toDto(find(id));
  }

  public LessonDto create(LessonDto d) {
    var e = new Lesson();
    apply(e, d);
    return toDto(repository.saveAndFlush(e));
  }

  public LessonDto update(Long id, LessonDto d) {
    var e = find(id);
    apply(e, d);
    return toDto(repository.saveAndFlush(e));
  }

  public void delete(Long id) {
    var e = find(id);
    repository.delete(e);
    repository.flush();
  }

  private void apply(Lesson e, LessonDto d) {
    if (!courses.existsById(d.courseId()))
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Course does not exist");
    e.setCourseId(d.courseId());
    e.setTitle(d.title());
    e.setContent(d.content());
  }

  private LessonDto toDto(Lesson e) {
    return new LessonDto(e.getId(), e.getCourseId(), e.getTitle(), e.getContent());
  }
}
