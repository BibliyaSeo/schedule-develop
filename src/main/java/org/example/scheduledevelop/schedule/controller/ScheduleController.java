package org.example.scheduledevelop.schedule.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.scheduledevelop.schedule.dto.CreateScheduleRequestDto;
import org.example.scheduledevelop.schedule.dto.ScheduleResponseDto;
import org.example.scheduledevelop.schedule.dto.UpdateScheduleRequestDto;
import org.example.scheduledevelop.schedule.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("LOGIN_USER") == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "로그인이 필요합니다.");
        }
        Long userId = (Long) session.getAttribute("LOGIN_USER");
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
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ScheduleResponseDto updateSchedule(@PathVariable Long id, @Valid @RequestBody UpdateScheduleRequestDto dto) {
//        if (dto.getTitle() == null && dto.getContents() == null) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "title 또는 contents의 값을 입력해 주세요.");
//        }
        return scheduleService.updateSchedule(id, dto.getTitle(), dto.getContents());
    }

    // 일정 삭제
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSchedule(@PathVariable Long id) {
        scheduleService.deleteSchedule(id);
    }
}
