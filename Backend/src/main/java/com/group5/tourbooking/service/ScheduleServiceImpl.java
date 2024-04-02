package com.group5.tourbooking.service;

import com.group5.tourbooking.dto.ScheduleDto;
import com.group5.tourbooking.dto.ScheduleDtoId;
import com.group5.tourbooking.mapper.ScheduleMapper;
import com.group5.tourbooking.model.Schedule;
import com.group5.tourbooking.model.Tour;
import com.group5.tourbooking.repository.ScheduleRepository;
import com.group5.tourbooking.repository.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScheduleServiceImpl implements ScheduleService{
    @Autowired
    ScheduleRepository scheduleRepository;
    @Autowired
    TourRepository tourRepository;
    @Autowired
    ScheduleMapper scheduleMapper;

    @Override
    public Schedule createSchedule(Schedule schedule) {
        return null;
    }

    @Override
    public ScheduleDto createSchedule(ScheduleDtoId scheduleDtoId) {
        Tour tour= tourRepository.findById(scheduleDtoId.getTourId())
                .orElseThrow(()->new RuntimeException("Tour no encontrado"));

        Schedule schedule= new Schedule();
        schedule.setTour(tour);
        schedule.setDaysOfWeek(scheduleDtoId.getDaysOfWeek());
        schedule.setMaxAvailability(scheduleDtoId.getMaxAvailability());
        scheduleRepository.save(schedule);

        ScheduleDto scheduleDto = scheduleMapper.scheduleToDto(schedule);

        return scheduleDto;
    }

    @Override
    public List<Schedule> findAllSchedule() {
        return scheduleRepository.findAll();
    }

    @Override
    public Optional<Schedule> findScheduleById(Long id) {
        return scheduleRepository.findById(id);
    }

    @Override
    public Schedule findScheduleByTourId(Long id) {
        return null;
    }

    @Override
    public Schedule updateSchedule(Schedule schedule) {
        return null;
    }

    @Override
    public void deleteScheduleById(Long id) {

    }
}
