package org.example.scheduledevelop.schedule.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.scheduledevelop.common.utils.SessionUtil;
import org.example.scheduledevelop.schedule.dto.CreateScheduleRequestDto;
import org.example.scheduledevelop.schedule.dto.ScheduleResponseDto;
import org.example.scheduledevelop.schedule.dto.UpdateScheduleRequestDto;
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
    public ScheduleResponseDto createSchedule(HttpServletRequest request, @Valid @RequestBody CreateScheduleRequestDto dto) {
        Long userId = SessionUtil.getLoginUserId(request);
        return scheduleService.createSchedule(userId, dto.getTitle(), dto.getContents());
    }

    // 일정 전체 조회(또는 작성자명 조회)
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ScheduleResponseDto> findScheduleByUsername(@RequestParam(required = false) String username) {
        return scheduleService.findScheduleByUsername(username);
    }

    // 일정 개별 조회
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ScheduleResponseDto findScheduleById(@PathVariable Long id) {
        return scheduleService.findScheduleById(id);
    }

    // 일정 수정
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ScheduleResponseDto updateSchedule(HttpServletRequest request, @PathVariable Long id, @Valid @RequestBody UpdateScheduleRequestDto dto) {
        Long userId = SessionUtil.getLoginUserId(request);
        return scheduleService.updateSchedule(id, userId, dto.getTitle(), dto.getContents());
    }

    // 일정 삭제
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSchedule(HttpServletRequest request, @PathVariable Long id) {
        Long userId = SessionUtil.getLoginUserId(request);
        scheduleService.deleteSchedule(id, userId);
    }
}
