package com.example.devicenote.service;

import com.example.devicenote.entity.DeviceNote;
import com.example.devicenote.repository.DeviceNoteRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DeviceNoteService {

 private final DeviceNoteRepository repo;

 public DeviceNoteService(DeviceNoteRepository repo){
  this.repo = repo;
 }

 public DeviceNote create(Long deviceId, String note, String user){
  if(note==null || note.trim().isEmpty()) throw new RuntimeException("Note cannot be blank");
  if(note.length()>1000) throw new RuntimeException("Note too long");
  if(user==null || user.trim().isEmpty()) throw new RuntimeException("Missing X-User header");

  DeviceNote dn = new DeviceNote();
  dn.setDeviceId(deviceId);
  dn.setNote(note);
  dn.setCreatedBy(user);

  return repo.save(dn);
 }

 public List<DeviceNote> list(Long deviceId, int limit){
  if(limit<1 || limit>100) throw new RuntimeException("Invalid limit");
  List<DeviceNote> list = repo.findByDeviceIdOrderByCreatedAtDesc(deviceId);
  return list.subList(0, Math.min(limit, list.size()));
 }
}
