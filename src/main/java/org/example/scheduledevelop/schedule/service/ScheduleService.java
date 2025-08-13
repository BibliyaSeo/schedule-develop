package org.example.scheduledevelop.schedule.service;

import org.example.scheduledevelop.schedule.dto.DetailScheduleResponseDto;
import org.example.scheduledevelop.schedule.dto.ScheduleResponseDto;
import org.example.scheduledevelop.schedule.entity.Schedule;
import org.springframework.data.domain.Page;

public interface ScheduleService {
    ScheduleResponseDto createSchedule(Long userId, String title, String contents);

    Page<ScheduleResponseDto> findScheduleByUsername(String username, int page, int size);

    DetailScheduleResponseDto findScheduleById(Long id);

    Schedule findEntityById(Long id);

    ScheduleResponseDto updateSchedule(Long id, Long userId, String title, String contents);

    void deleteSchedule(Long id, Long userId);
}
