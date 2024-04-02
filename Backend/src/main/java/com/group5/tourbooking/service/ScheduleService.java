package com.group5.tourbooking.service;

import com.group5.tourbooking.dto.ScheduleDto;
import com.group5.tourbooking.dto.ScheduleDtoId;
import com.group5.tourbooking.model.Schedule;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public interface ScheduleService {
    Schedule createSchedule(Schedule schedule);

    ScheduleDto createSchedule(ScheduleDtoId scheduleDtoId);

    List<Schedule> findAllSchedule();
    Optional<Schedule> findScheduleById(Long id);
    Schedule findScheduleByTourId(Long id);
    Schedule updateSchedule(Schedule schedule);
    void deleteScheduleById(Long id);
}
