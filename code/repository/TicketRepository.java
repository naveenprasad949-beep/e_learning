package com.learnhub.repository;

import com.learnhub.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
  java.util.List<Ticket> findByOwnerId(Long ownerId);
}
