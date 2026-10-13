package com.learnhub.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.FetchType;
import jakarta.persistence.Table;

@Entity
@Table(name = "payments")
public class Payment extends BaseEntity {
  @Column(nullable = false) private Long courseId;
  @Column(nullable = false) private Long ownerId;
  @Column(nullable = false) private java.math.BigDecimal amount;
  @Column(nullable = false, length = 30) private String method;
  @Column(nullable = false, length = 20) private String status;
  @Column(nullable = false, length = 20) private String paymentDate;

  public Long getCourseId() { return courseId; }
  public void setCourseId(Long value) { courseId = value; }
  public Long getOwnerId() { return ownerId; }
  public void setOwnerId(Long value) { ownerId = value; }
  public java.math.BigDecimal getAmount() { return amount; }
  public void setAmount(java.math.BigDecimal value) { amount = value; }
  public String getMethod() { return method; }
  public void setMethod(String value) { method = value; }
  public String getStatus() { return status; }
  public void setStatus(String value) { status = value; }
  public String getPaymentDate() { return paymentDate; }
  public void setPaymentDate(String value) { paymentDate = value; }

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "courseId", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "fk_payments_course"))
  private Course course;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "ownerId", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "fk_payments_owner"))
  private Customer owner;
}
