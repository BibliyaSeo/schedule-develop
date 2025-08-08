package org.example.scheduledevelop.schedule.service;

import org.example.scheduledevelop.schedule.dto.ScheduleResponseDto;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ScheduleService {
    ScheduleResponseDto createSchedule(@RequestBody String username, String title, String contents);

    List<ScheduleResponseDto> findScheduleByUsername(String username);

}
