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
public class TicketService implements CrudService<TicketDto> {
  private final TicketRepository repository;
  private final CurrentUser current;
  private final CourseRepository courses;
  private final PasswordEncoder encoder;

  public TicketService(
      TicketRepository repository,
      CurrentUser current,
      CourseRepository courses,
      PasswordEncoder encoder) {
    this.repository = repository;
    this.current = current;
    this.courses = courses;
    this.encoder = encoder;
  }

  private Ticket find(Long id) {
    var e =
        repository
            .findById(id)
            .orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ticket not found"));
    current.requireOwner(e.getOwnerId());
    return e;
  }

  public List<TicketDto> list() {
    return (current.isAdmin() ? repository.findAll() : repository.findByOwnerId(current.id()))
        .stream().map(this::toDto).toList();
  }

  public TicketDto get(Long id) {
    return toDto(find(id));
  }

  public TicketDto create(TicketDto d) {
    var e = new Ticket();
    e.setOwnerId(current.id());
    apply(e, d);
    return toDto(repository.saveAndFlush(e));
  }

  public TicketDto update(Long id, TicketDto d) {
    var e = find(id);
    apply(e, d);
    return toDto(repository.saveAndFlush(e));
  }

  public void delete(Long id) {
    var e = find(id);
    repository.delete(e);
    repository.flush();
  }

  private void apply(Ticket e, TicketDto d) {
    e.setSubject(d.subject());
    e.setMessage(d.message());
    if (current.isAdmin()) {
      e.setStatus(d.status());
      e.setReply(d.reply());
    } else if (e.getId() == null) {
      e.setStatus("OPEN");
      e.setReply("");
    }
  }

  private TicketDto toDto(Ticket e) {
    return new TicketDto(
        e.getId(), e.getSubject(), e.getMessage(), e.getStatus(), e.getReply(), e.getOwnerId());
  }
}
