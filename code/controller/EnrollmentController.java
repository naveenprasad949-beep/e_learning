package com.learnhub.controller;

import com.learnhub.dto.EnrollmentDto;
import com.learnhub.service.EnrollmentService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {
  private final EnrollmentService service;

  public EnrollmentController(EnrollmentService service) {
    this.service = service;
  }

  @GetMapping
  public List<EnrollmentDto> list() {
    return service.list();
  }

  @GetMapping("/{id}")
  public EnrollmentDto get(@PathVariable Long id) {
    return service.get(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public EnrollmentDto create(@Valid @RequestBody EnrollmentDto dto) {
    return service.create(dto);
  }

  @PutMapping("/{id}")
  public EnrollmentDto update(@PathVariable Long id, @Valid @RequestBody EnrollmentDto dto) {
    return service.update(id, dto);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable Long id) {
    service.delete(id);
  }
}
