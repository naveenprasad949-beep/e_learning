package com.learnhub.controller;

import com.learnhub.dto.PaymentDto;
import com.learnhub.service.PaymentService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {
  private final PaymentService service;
  public PaymentController(PaymentService service) { this.service = service; }
  @GetMapping public List<PaymentDto> list() { return service.list(); }
  @GetMapping("/{id}") public PaymentDto get(@PathVariable Long id) { return service.get(id); }
  @PostMapping @ResponseStatus(HttpStatus.CREATED) public PaymentDto create(@Valid @RequestBody PaymentDto dto) { return service.create(dto); }
  @PutMapping("/{id}") public PaymentDto update(@PathVariable Long id, @Valid @RequestBody PaymentDto dto) { return service.update(id,dto); }
  @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void delete(@PathVariable Long id) { service.delete(id); }
}
