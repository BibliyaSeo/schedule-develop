package org.example.scheduledevelop.schedule.controller;

import lombok.RequiredArgsConstructor;
import org.example.scheduledevelop.schedule.dto.CreateScheduleRequestDto;
import org.example.scheduledevelop.schedule.dto.ScheduleResponseDto;
import org.example.scheduledevelop.schedule.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/schedule")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ScheduleResponseDto createSchedule(@RequestBody CreateScheduleRequestDto dto) {
        return scheduleService.save(dto.getUsername(), dto.getTitle(), dto.getContents());
    }
}
