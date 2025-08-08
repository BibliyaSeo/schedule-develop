package org.example.scheduledevelop.schedule.service;

import lombok.RequiredArgsConstructor;
import org.example.scheduledevelop.schedule.dto.ScheduleResponseDto;
import org.example.scheduledevelop.schedule.entity.Schedule;
import org.example.scheduledevelop.schedule.repository.ScheduleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Override
    public ScheduleResponseDto save(String username, String title, String contents) {
        Schedule saveSchedule = scheduleRepository.save(new Schedule(username, title, contents));
        return new ScheduleResponseDto(saveSchedule.getId(),
                saveSchedule.getUsername(),
                saveSchedule.getTitle(),
                saveSchedule.getContents(),
                saveSchedule.getCreatedAt(),
                saveSchedule.getUpdatedAt());
    }
}
