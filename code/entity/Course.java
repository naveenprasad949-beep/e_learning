package com.learnhub.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "courses")
public class Course extends BaseEntity {
  @Column(nullable = false)
  private String title;

  public String getTitle() {
    return title;
  }

  public void setTitle(String value) {
    this.title = value;
  }

  @Column(nullable = false)
  private String category;

  public String getCategory() {
    return category;
  }

  public void setCategory(String value) {
    this.category = value;
  }

  @Column(nullable = false, length = 2000)
  private String description;

  public String getDescription() {
    return description;
  }

  public void setDescription(String value) {
    this.description = value;
  }
}
