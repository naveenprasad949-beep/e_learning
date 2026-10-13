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
public class CustomerService implements CrudService<CustomerDto> {
  private final CustomerRepository repository;
  private final CurrentUser current;
  private final CourseRepository courses;
  private final PasswordEncoder encoder;

  public CustomerService(
      CustomerRepository repository,
      CurrentUser current,
      CourseRepository courses,
      PasswordEncoder encoder) {
    this.repository = repository;
    this.current = current;
    this.courses = courses;
    this.encoder = encoder;
  }

  private Customer find(Long id) {
    var e =
        repository
            .findById(id)
            .orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));
    return e;
  }

  public List<CustomerDto> list() {
    return repository.findAll().stream().map(this::toDto).toList();
  }

  public CustomerDto get(Long id) {
    return toDto(find(id));
  }

  public CustomerDto create(CustomerDto d) {
    var e = new Customer();
    if (d.password() == null || d.password().isBlank())
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password required");
    e.setRole("CUSTOMER");
    apply(e, d);
    return toDto(repository.saveAndFlush(e));
  }

  public CustomerDto update(Long id, CustomerDto d) {
    var e = find(id);
    apply(e, d);
    return toDto(repository.saveAndFlush(e));
  }

  public void delete(Long id) {
    var e = find(id);
    if ("ADMIN".equals(e.getRole()))
      throw new ResponseStatusException(
          HttpStatus.CONFLICT, "Administrator accounts cannot be deleted");
    repository.delete(e);
    repository.flush();
  }

  private void apply(Customer e, CustomerDto d) {
    e.setName(d.name());
    e.setEmail(d.email().trim().toLowerCase(java.util.Locale.ROOT));
    if (d.password() != null && !d.password().isBlank())
      e.setPasswordHash(encoder.encode(d.password()));
  }

  private CustomerDto toDto(Customer e) {
    return new CustomerDto(e.getId(), e.getName(), e.getEmail(), null);
  }
}
