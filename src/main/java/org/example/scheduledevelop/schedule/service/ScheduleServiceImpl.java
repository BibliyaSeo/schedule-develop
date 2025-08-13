package org.example.scheduledevelop.schedule.service;

import lombok.RequiredArgsConstructor;
import org.example.scheduledevelop.comments.dto.CommentResponseDto;
import org.example.scheduledevelop.comments.repository.CommentRepository;
import org.example.scheduledevelop.schedule.dto.DetailScheduleResponseDto;
import org.example.scheduledevelop.schedule.dto.ScheduleResponseDto;
import org.example.scheduledevelop.schedule.entity.Schedule;
import org.example.scheduledevelop.schedule.repository.ScheduleRepository;
import org.example.scheduledevelop.users.entity.User;
import org.example.scheduledevelop.users.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    // 일정 생성
    @Override
    public ScheduleResponseDto createSchedule(Long userId, String title, String contents) {
        User findUser = userRepository.findByIdOrElseThrow(userId);
        Schedule saveSchedule = scheduleRepository.save(new Schedule(findUser, title, contents));
        return new ScheduleResponseDto(saveSchedule, 0);
    }

    // 일정 전체 조회(또는 작성자명 조회)
    @Override
    @Transactional(readOnly = true)
    public Page<ScheduleResponseDto> findScheduleByUsername(String username, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("updatedAt").descending());
        return scheduleRepository.findAllWithCommentCountAndUsername(username, pageable);
    }

    // 일정 개별 조회
    @Override
    @Transactional(readOnly = true)
    public DetailScheduleResponseDto findScheduleById(Long id) {
        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);

        List<CommentResponseDto> comments = commentRepository.findAllByScheduleIdOrderByCreatedAtDesc(id)
                .stream()
                .map(CommentResponseDto::new)
                .toList();

        return new DetailScheduleResponseDto(findSchedule, comments);
    }

    // 엔티티 반환용
    @Override
    public Schedule findEntityById(Long id) {
        return scheduleRepository.findByIdOrElseThrow(id);
    }

    // 일정 수정
    @Override
    public ScheduleResponseDto updateSchedule(Long id, Long userId, String title, String contents) {
        if (title == null && contents == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "title 또는 contents를 입력해 주세요.");
        }

        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);

        // 유저 아이디와 수정하려는 아이디가 같다면
        if (!findSchedule.getUser().getId().equals(userId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "수정 권한이 없습니다.");
        }
        findSchedule.updateSchedule(title, contents);
        long commentCount = commentRepository.countByScheduleId(findSchedule.getId());
        return new ScheduleResponseDto(findSchedule, commentCount);
    }

    @Override
    public void deleteSchedule(Long id, Long userId) {
        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);
        if (!findSchedule.getUser().getId().equals(userId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "삭제 권한이 없습니다.");
        }
        scheduleRepository.delete(findSchedule);
    }
}
