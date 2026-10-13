package com.learnhub.controller;

import com.learnhub.dto.LessonDto;
import com.learnhub.service.LessonService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/lessons")
public class LessonController {
  private final LessonService service;

  public LessonController(LessonService service) {
    this.service = service;
  }

  @GetMapping
  public List<LessonDto> list() {
    return service.list();
  }

  @GetMapping("/{id}")
  public LessonDto get(@PathVariable Long id) {
    return service.get(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public LessonDto create(@Valid @RequestBody LessonDto dto) {
    return service.create(dto);
  }

  @PutMapping("/{id}")
  public LessonDto update(@PathVariable Long id, @Valid @RequestBody LessonDto dto) {
    return service.update(id, dto);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable Long id) {
    service.delete(id);
  }
}
