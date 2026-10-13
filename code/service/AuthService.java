package com.learnhub.service;

import com.learnhub.dto.AuthDtos.*;
import com.learnhub.entity.Customer;
import com.learnhub.repository.CustomerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthService {
  private final CustomerRepository users;
  private final PasswordEncoder encoder;

  public AuthService(CustomerRepository users, PasswordEncoder encoder) {
    this.users = users;
    this.encoder = encoder;
  }

  public User login(Login d, String role) {
    var u =
            users
                    .findByEmail(d.email().trim().toLowerCase(java.util.Locale.ROOT))
                    .orElseThrow(() -> badLogin());
    if (!encoder.matches(d.password(), u.getPasswordHash()) || !role.equals(u.getRole()))
      throw badLogin();
    return view(u);
  }

  private ResponseStatusException badLogin() {
    return new ResponseStatusException(
            HttpStatus.UNAUTHORIZED, "Invalid credentials or wrong login portal");
  }

  public User register(Register d) {
    var u = new Customer();
    u.setName(d.name());
    u.setEmail(d.email().trim().toLowerCase(java.util.Locale.ROOT));
    u.setPasswordHash(encoder.encode(d.password()));
    u.setRole("CUSTOMER");
    return view(users.saveAndFlush(u));
  }

  public User me(String email) {
    return view(users.findByEmail(email).orElseThrow(() -> badLogin()));
  }

  private User view(Customer u) {
    return new User(u.getId(), u.getName(), u.getEmail(), u.getRole());
  }
}