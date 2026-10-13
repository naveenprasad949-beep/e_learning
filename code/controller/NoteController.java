package com.learnhub.controller;

import com.learnhub.dto.NoteDto;
import com.learnhub.service.NoteService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notes")
public class NoteController {
  private final NoteService service;

  public NoteController(NoteService service) {
    this.service = service;
  }

  @GetMapping
  public List<NoteDto> list() {
    return service.list();
  }

  @GetMapping("/{id}")
  public NoteDto get(@PathVariable Long id) {
    return service.get(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public NoteDto create(@Valid @RequestBody NoteDto dto) {
    return service.create(dto);
  }

  @PutMapping("/{id}")
  public NoteDto update(@PathVariable Long id, @Valid @RequestBody NoteDto dto) {
    return service.update(id, dto);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable Long id) {
    service.delete(id);
  }
}
