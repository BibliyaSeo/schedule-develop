package org.example.scheduledevelop.schedule.controller;

import lombok.RequiredArgsConstructor;
import org.example.scheduledevelop.schedule.dto.CreateScheduleRequestDto;
import org.example.scheduledevelop.schedule.dto.ScheduleResponseDto;
import org.example.scheduledevelop.schedule.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedule")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    // 일정 생성
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ScheduleResponseDto createSchedule(@RequestBody CreateScheduleRequestDto dto) {
        return scheduleService.save(dto.getUsername(), dto.getTitle(), dto.getContents());
    }

    // 전체 조회(또는 작성자명 조회)
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ScheduleResponseDto> findAllScheduleByUsername(@RequestParam(required = false) String username) {
        return scheduleService.findScheduleByUsername(username);
    }
}
