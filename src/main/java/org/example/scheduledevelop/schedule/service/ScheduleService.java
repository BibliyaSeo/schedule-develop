package org.example.scheduledevelop.schedule.service;

import org.example.scheduledevelop.schedule.dto.ScheduleResponseDto;
import org.springframework.web.bind.annotation.RequestBody;

public interface ScheduleService {
    ScheduleResponseDto save(@RequestBody String username, String title, String contents);
}
