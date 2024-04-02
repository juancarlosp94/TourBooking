package com.group5.tourbooking.mapper;

import com.group5.tourbooking.dto.ScheduleDto;
import com.group5.tourbooking.model.Schedule;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public class ScheduleMapper {
    public ScheduleDto scheduleToDto(Schedule schedule){
        ScheduleDto scheduleDto = new ScheduleDto();

        scheduleDto.setTour(schedule.getTour());
        scheduleDto.setDaysOfWeek(schedule.getDaysOfWeek());
        scheduleDto.setMaxAvailability(schedule.getMaxAvailability());

        return scheduleDto;
    }
}
