package com.learnhub.controller;

import com.learnhub.dto.AuthDtos.*;
import com.learnhub.service.AuthService;
import jakarta.servlet.http.*;
import jakarta.validation.Valid;
import java.util.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

  private final AuthService service;
  private final HttpSessionSecurityContextRepository repo;

  public AuthController(AuthService service, HttpSessionSecurityContextRepository repo) {
    this.service = service;
    this.repo = repo;
  }

  @GetMapping("/csrf")
  public Map<String, String> csrf(CsrfToken token) {
    // CSRF disabled නම් token null වෙන්න පුළුවන් — safe check
    if (token == null) {
      return Map.of("token", "csrf-disabled", "headerName", "X-CSRF-TOKEN");
    }
    return Map.of("token", token.getToken(), "headerName", token.getHeaderName());
  }

  @PostMapping("/customer/login")
  public User customerLogin(
          @Valid @RequestBody Login d, HttpServletRequest req, HttpServletResponse res) {
    return login(d, "CUSTOMER", req, res);
  }

  @PostMapping("/admin/login")
  public User adminLogin(
          @Valid @RequestBody Login d, HttpServletRequest req, HttpServletResponse res) {
    return login(d, "ADMIN", req, res);
  }

  private User login(Login d, String role, HttpServletRequest req, HttpServletResponse res) {
    var user = service.login(d, role);
    req.getSession();
    req.changeSessionId();
    var context = SecurityContextHolder.createEmptyContext();
    context.setAuthentication(
            new UsernamePasswordAuthenticationToken(
                    user.email(), null, List.of(new SimpleGrantedAuthority("ROLE_" + role))));
    SecurityContextHolder.setContext(context);
    repo.saveContext(context, req, res);
    return user;
  }

  @PostMapping("/register")
  @ResponseStatus(HttpStatus.CREATED)
  public User register(@Valid @RequestBody Register d) {
    return service.register(d);
  }

  @GetMapping("/me")
  public ResponseEntity<?> me(Authentication a) {
    // Login කරලා නැත්නම් authentication null වෙනවා
    if (a == null || a.getName() == null || !a.isAuthenticated()) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
              .body(Map.of("message", "Not authenticated"));
    }
    try {
      User user = service.me(a.getName());
      return ResponseEntity.ok(user);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
              .body(Map.of("message", "User session invalid"));
    }
  }

  @PostMapping("/logout")
  public ResponseEntity<?> logout(HttpServletRequest req, HttpServletResponse res) {
    try {
      SecurityContextHolder.clearContext();
      var session = req.getSession(false);
      if (session != null) {
        session.invalidate();
      }
      return ResponseEntity.ok(Map.of("message", "Logged out"));
    } catch (Exception e) {
      return ResponseEntity.ok(Map.of("message", "Logged out"));
    }
  }
}