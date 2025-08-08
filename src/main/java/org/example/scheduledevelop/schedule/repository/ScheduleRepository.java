package org.example.scheduledevelop.schedule.repository;

import org.example.scheduledevelop.common.exception.ErrorCode;
import org.example.scheduledevelop.common.exception.MyCustomException;
import org.example.scheduledevelop.schedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findByUser_UsernameOrderByCreatedAtDesc(String username);

    default Schedule findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(() -> new MyCustomException(ErrorCode.POST_NOT_FOUND));
    }
}
