package com.learnhub.entity;

import jakarta.persistence.*;

@Entity
@Table(
    name = "enrollments",
    uniqueConstraints = @UniqueConstraint(columnNames = {"ownerId", "courseId"}))
public class Enrollment extends BaseEntity {
  @Column(nullable = false)
  private Long courseId;

  public Long getCourseId() {
    return courseId;
  }

  public void setCourseId(Long value) {
    this.courseId = value;
  }

  @Column(nullable = false)
  private Integer progress;

  public Integer getProgress() {
    return progress;
  }

  public void setProgress(Integer value) {
    this.progress = value;
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
      foreignKey = @ForeignKey(name = "fk_enrollments_course"))
  private Course course;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(
      name = "ownerId",
      insertable = false,
      updatable = false,
      foreignKey = @ForeignKey(name = "fk_enrollments_owner"))
  private Customer owner;
}
