package com.learnhub.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "notes")
public class Note extends BaseEntity {
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

  @Column(nullable = false)
  private Long ownerId;

  public Long getOwnerId() {
    return ownerId;
  }

  public void setOwnerId(Long value) {
    this.ownerId = value;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(
      name = "courseId",
      insertable = false,
      updatable = false,
      foreignKey = @ForeignKey(name = "fk_notes_course"))
  private Course course;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(
      name = "ownerId",
      insertable = false,
      updatable = false,
      foreignKey = @ForeignKey(name = "fk_notes_owner"))
  private Customer owner;
}
