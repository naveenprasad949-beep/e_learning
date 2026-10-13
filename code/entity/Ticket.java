package com.learnhub.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tickets")
public class Ticket extends BaseEntity {
  @Column(nullable = false)
  private String subject;

  public String getSubject() {
    return subject;
  }

  public void setSubject(String value) {
    this.subject = value;
  }

  @Column(nullable = false, length = 2000)
  private String message;

  public String getMessage() {
    return message;
  }

  public void setMessage(String value) {
    this.message = value;
  }

  @Column(nullable = false)
  private String status;

  public String getStatus() {
    return status;
  }

  public void setStatus(String value) {
    this.status = value;
  }

  @Column(length = 2000)
  private String reply;

  public String getReply() {
    return reply;
  }

  public void setReply(String value) {
    this.reply = value;
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
      name = "ownerId",
      insertable = false,
      updatable = false,
      foreignKey = @ForeignKey(name = "fk_tickets_owner"))
  private Customer owner;
}
