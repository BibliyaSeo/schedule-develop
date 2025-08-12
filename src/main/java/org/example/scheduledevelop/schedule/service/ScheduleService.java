package org.example.scheduledevelop.schedule.service;

import org.example.scheduledevelop.schedule.dto.ScheduleResponseDto;
import org.example.scheduledevelop.schedule.entity.Schedule;

import java.util.List;

public interface ScheduleService {
    ScheduleResponseDto createSchedule(Long userId, String title, String contents);

    List<ScheduleResponseDto> findScheduleByUsername(String username);

    ScheduleResponseDto findScheduleById(Long id);

    Schedule findEntityById(Long id);

    ScheduleResponseDto updateSchedule(Long id, String title, String contents);

    void deleteSchedule(Long id);
}
