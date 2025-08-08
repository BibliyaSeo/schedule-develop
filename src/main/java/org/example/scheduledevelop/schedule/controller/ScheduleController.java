package org.example.scheduledevelop.schedule.controller;

import lombok.RequiredArgsConstructor;
import org.example.scheduledevelop.schedule.service.ScheduleService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/schedule")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;
}
