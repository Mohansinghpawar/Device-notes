package com.example.devicenote.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="device_note")
public class DeviceNote {

 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;

 @Column(name="device_id", nullable=false)
 private Long deviceId;

 @Column(length=1000, nullable=false)
 private String note;

 @Column(name="created_at")
 private LocalDateTime createdAt;

 @Column(name="created_by", nullable=false)
 private String createdBy;

 @PrePersist
 public void prePersist(){
  this.createdAt = LocalDateTime.now();
 }

 public Long getId(){return id;}
 public Long getDeviceId(){return deviceId;}
 public void setDeviceId(Long d){this.deviceId=d;}
 public String getNote(){return note;}
 public void setNote(String n){this.note=n;}
 public LocalDateTime getCreatedAt(){return createdAt;}
 public String getCreatedBy(){return createdBy;}
 public void setCreatedBy(String c){this.createdBy=c;}
}
