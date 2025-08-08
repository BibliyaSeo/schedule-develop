package org.example.scheduledevelop.schedule.service;

import lombok.RequiredArgsConstructor;
import org.example.scheduledevelop.schedule.dto.ScheduleResponseDto;
import org.example.scheduledevelop.schedule.entity.Schedule;
import org.example.scheduledevelop.schedule.repository.ScheduleRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;

    // 일정 생성
    @Override
    public ScheduleResponseDto createSchedule(String username, String title, String contents) {
        Schedule saveSchedule = scheduleRepository.save(new Schedule(username, title, contents));
        return new ScheduleResponseDto(saveSchedule);
    }

    // 일정 전체 조회(또는 작성자명 조회)
    @Override
    @Transactional(readOnly = true)
    public List<ScheduleResponseDto> findScheduleByUsername(String username) {
        List<Schedule> schedules;
        if (username == null || username.isEmpty()) {
            schedules = scheduleRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
        } else {
            schedules = scheduleRepository.findByUsernameOrderByCreatedAtDesc(username);
        }
        return schedules.stream().map(ScheduleResponseDto::new).toList();
    }

    // 일정 개별 조회
    @Override
    @Transactional(readOnly = true)
    public ScheduleResponseDto findScheduleById(Long id) {
        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);
        return new ScheduleResponseDto(findSchedule);
    }

    // 일정 수정
    @Override
    public ScheduleResponseDto updateSchedule(Long id, String title, String contents) {
        if (title == null && contents == null) {
            throw new IllegalArgumentException("title 또는 contents를 입력해 주세요.");
        }
        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);

        findSchedule.updateSchedule(title, contents);
        return new ScheduleResponseDto(findSchedule);
    }


}
