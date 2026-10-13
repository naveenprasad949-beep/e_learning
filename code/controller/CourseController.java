package com.learnhub.controller;

import com.learnhub.dto.CourseDto;
import com.learnhub.service.CourseService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/courses")
public class CourseController {
  private final CourseService service;

  public CourseController(CourseService service) {
    this.service = service;
  }

  @GetMapping
  public List<CourseDto> list() {
    return service.list();
  }

  @GetMapping("/{id}")
  public CourseDto get(@PathVariable Long id) {
    return service.get(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public CourseDto create(@Valid @RequestBody CourseDto dto) {
    return service.create(dto);
  }

  @PutMapping("/{id}")
  public CourseDto update(@PathVariable Long id, @Valid @RequestBody CourseDto dto) {
    return service.update(id, dto);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable Long id) {
    service.delete(id);
  }
}
