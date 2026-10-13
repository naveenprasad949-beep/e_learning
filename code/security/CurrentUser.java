package com.learnhub.security;

import com.learnhub.repository.CustomerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class CurrentUser {
  private final CustomerRepository users;

  public CurrentUser(CustomerRepository users) {
    this.users = users;
  }

  public Long id() {
    return users
        .findByEmail(SecurityContextHolder.getContext().getAuthentication().getName())
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED))
        .getId();
  }

  public boolean isAdmin() {
    return SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
        .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
  }

  public void requireOwner(Long owner) {
    if (!isAdmin() && !id().equals(owner))
      throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Access denied");
  }
}
