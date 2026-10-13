package com.learnhub.repository;

import com.learnhub.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
  java.util.Optional<Customer> findByEmail(String email);
}
