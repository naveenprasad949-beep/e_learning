package com.learnhub.controller;

import com.learnhub.dto.TicketDto;
import com.learnhub.service.TicketService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {
  private final TicketService service;

  public TicketController(TicketService service) {
    this.service = service;
  }

  @GetMapping
  public List<TicketDto> list() {
    return service.list();
  }

  @GetMapping("/{id}")
  public TicketDto get(@PathVariable Long id) {
    return service.get(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public TicketDto create(@Valid @RequestBody TicketDto dto) {
    return service.create(dto);
  }

  @PutMapping("/{id}")
  public TicketDto update(@PathVariable Long id, @Valid @RequestBody TicketDto dto) {
    return service.update(id, dto);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable Long id) {
    service.delete(id);
  }
}
