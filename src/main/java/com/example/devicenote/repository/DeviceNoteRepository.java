package com.example.devicenote.repository;

import com.example.devicenote.entity.DeviceNote;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DeviceNoteRepository extends JpaRepository<DeviceNote, Long>{
 List<DeviceNote> findByDeviceIdOrderByCreatedAtDesc(Long deviceId);
}
