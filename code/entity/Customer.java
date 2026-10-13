package com.learnhub.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "customers")
public class Customer extends BaseEntity {
  @Column(nullable = false)
  private String name;

  public String getName() {
    return name;
  }

  public void setName(String value) {
    this.name = value;
  }

  @Column(nullable = false, unique = true)
  private String email;

  public String getEmail() {
    return email;
  }

  public void setEmail(String value) {
    this.email = value;
  }

  @Column(nullable = false)
  private String passwordHash;

  public String getPasswordHash() {
    return passwordHash;
  }

  public void setPasswordHash(String value) {
    this.passwordHash = value;
  }

  @Column(nullable = false)
  private String role;

  public String getRole() {
    return role;
  }

  public void setRole(String value) {
    this.role = value;
  }
}
