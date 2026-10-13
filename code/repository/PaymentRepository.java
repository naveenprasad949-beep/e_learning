package com.learnhub.repository;

import com.learnhub.entity.Payment;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
  List<Payment> findByOwnerId(Long ownerId);
}
