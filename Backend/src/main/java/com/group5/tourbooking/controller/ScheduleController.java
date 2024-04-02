package com.group5.tourbooking.controller;

import com.group5.tourbooking.dto.ScheduleDto;
import com.group5.tourbooking.dto.ScheduleDtoId;
import com.group5.tourbooking.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/schedule")
@CrossOrigin(origins = "*")
public class ScheduleController {
    @Autowired
    ScheduleService scheduleService;

    @PostMapping
    public ScheduleDto createSchedule(@RequestBody ScheduleDtoId scheduleDtoId){
        return scheduleService.createSchedule(scheduleDtoId);
    }
}
