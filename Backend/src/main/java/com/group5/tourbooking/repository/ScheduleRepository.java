package com.group5.tourbooking.repository;

import com.group5.tourbooking.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long>  {
    Schedule findByTourId(Long tourId);
}
