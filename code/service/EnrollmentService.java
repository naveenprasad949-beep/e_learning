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
public class EnrollmentService implements CrudService<EnrollmentDto> {
  private final EnrollmentRepository repository;
  private final CurrentUser current;
  private final CourseRepository courses;
  private final PasswordEncoder encoder;

  public EnrollmentService(
      EnrollmentRepository repository,
      CurrentUser current,
      CourseRepository courses,
      PasswordEncoder encoder) {
    this.repository = repository;
    this.current = current;
    this.courses = courses;
    this.encoder = encoder;
  }

  private Enrollment find(Long id) {
    var e =
        repository
            .findById(id)
            .orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Enrollment not found"));
    current.requireOwner(e.getOwnerId());
    return e;
  }

  public List<EnrollmentDto> list() {
    return (current.isAdmin() ? repository.findAll() : repository.findByOwnerId(current.id()))
        .stream().map(this::toDto).toList();
  }

  public EnrollmentDto get(Long id) {
    return toDto(find(id));
  }

  public EnrollmentDto create(EnrollmentDto d) {
    var e = new Enrollment();
    e.setOwnerId(current.id());
    apply(e, d);
    return toDto(repository.saveAndFlush(e));
  }

  public EnrollmentDto update(Long id, EnrollmentDto d) {
    var e = find(id);
    apply(e, d);
    return toDto(repository.saveAndFlush(e));
  }

  public void delete(Long id) {
    var e = find(id);
    repository.delete(e);
    repository.flush();
  }

  private void apply(Enrollment e, EnrollmentDto d) {
    if (!courses.existsById(d.courseId()))
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Course does not exist");
    if ((e.getId() == null || !e.getCourseId().equals(d.courseId()))
        && repository.existsByOwnerIdAndCourseId(e.getOwnerId(), d.courseId()))
      throw new ResponseStatusException(HttpStatus.CONFLICT, "Already enrolled in this course");
    e.setCourseId(d.courseId());
    e.setProgress(d.progress());
  }

  private EnrollmentDto toDto(Enrollment e) {
    return new EnrollmentDto(e.getId(), e.getCourseId(), e.getProgress(), e.getOwnerId());
  }
}
