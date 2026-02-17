package com.example.devicenote.controller;

import com.example.devicenote.entity.DeviceNote;
import com.example.devicenote.service.DeviceNoteService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/devices")
public class DeviceNoteController {

 private final DeviceNoteService service;

 public DeviceNoteController(DeviceNoteService service){
  this.service = service;
 }

 @PostMapping("/{deviceId}/notes")
 public DeviceNote create(@PathVariable Long deviceId,
                          @RequestHeader(value="X-User", required=false) String user,
                          @RequestBody Map<String,String> body){
  return service.create(deviceId, body.get("note"), user);
 }

 @GetMapping("/{deviceId}/notes")
 public List<DeviceNote> list(@PathVariable Long deviceId,
                              @RequestParam(defaultValue="20") int limit){
  return service.list(deviceId, limit);
 }
}
