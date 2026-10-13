package com.learnhub.service;

import com.learnhub.dto.PaymentDto;
import com.learnhub.entity.Payment;
import com.learnhub.repository.CourseRepository;
import com.learnhub.repository.PaymentRepository;
import com.learnhub.security.CurrentUser;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@Transactional
public class PaymentService implements CrudService<PaymentDto> {
  private final PaymentRepository repository;
  private final CourseRepository courses;
  private final CurrentUser current;
  public PaymentService(PaymentRepository repository, CourseRepository courses, CurrentUser current) {
    this.repository = repository; this.courses = courses; this.current = current;
  }
  public List<PaymentDto> list() {
    return (current.isAdmin() ? repository.findAll() : repository.findByOwnerId(current.id())).stream().map(this::dto).toList();
  }
  public PaymentDto get(Long id) { return dto(find(id)); }
  public PaymentDto create(PaymentDto d) {
    checkCourse(d); Payment p = new Payment(); p.setOwnerId(current.id()); apply(p,d); return dto(repository.saveAndFlush(p));
  }
  public PaymentDto update(Long id, PaymentDto d) {
    Payment p = find(id); checkCourse(d); apply(p,d); return dto(repository.saveAndFlush(p));
  }
  public void delete(Long id) { Payment p = find(id); repository.delete(p); repository.flush(); }
  private Payment find(Long id) { Payment p=repository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Payment record not found")); current.requireOwner(p.getOwnerId()); return p; }
  private void checkCourse(PaymentDto d) { if(!courses.existsById(d.courseId())) throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Course does not exist"); }
  private void apply(Payment p, PaymentDto d) { p.setCourseId(d.courseId()); p.setAmount(d.amount()); p.setMethod(d.method()); p.setStatus(d.status()); p.setPaymentDate(d.paymentDate()); }
  private PaymentDto dto(Payment p) { return new PaymentDto(p.getId(),p.getCourseId(),p.getAmount(),p.getMethod(),p.getStatus(),p.getPaymentDate(),p.getOwnerId()); }
}
