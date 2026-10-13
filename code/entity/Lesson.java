package com.learnhub.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "lessons")
public class Lesson extends BaseEntity {
  @Column(nullable = false)
  private Long courseId;

  public Long getCourseId() {
    return courseId;
  }

  public void setCourseId(Long value) {
    this.courseId = value;
  }

  @Column(nullable = false)
  private String title;

  public String getTitle() {
    return title;
  }

  public void setTitle(String value) {
    this.title = value;
  }

  @Column(nullable = false, length = 2000)
  private String content;

  public String getContent() {
    return content;
  }

  public void setContent(String value) {
    this.content = value;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(
      name = "courseId",
      insertable = false,
      updatable = false,
      foreignKey = @ForeignKey(name = "fk_lessons_course"))
  private Course course;
}
