package com.learnhub.controller;

import com.learnhub.dto.CustomerDto;
import com.learnhub.service.CustomerService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
  private final CustomerService service;

  public CustomerController(CustomerService service) {
    this.service = service;
  }

  @GetMapping
  public List<CustomerDto> list() {
    return service.list();
  }

  @GetMapping("/{id}")
  public CustomerDto get(@PathVariable Long id) {
    return service.get(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public CustomerDto create(@Valid @RequestBody CustomerDto dto) {
    return service.create(dto);
  }

  @PutMapping("/{id}")
  public CustomerDto update(@PathVariable Long id, @Valid @RequestBody CustomerDto dto) {
    return service.update(id, dto);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable Long id) {
    service.delete(id);
  }
}
