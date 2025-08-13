package org.example.scheduledevelop.schedule.repository;

import org.example.scheduledevelop.common.exception.ErrorCode;
import org.example.scheduledevelop.common.exception.MyCustomException;
import org.example.scheduledevelop.schedule.dto.ScheduleResponseDto;
import org.example.scheduledevelop.schedule.entity.Schedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
//    List<Schedule> findByUser_UsernameOrderByCreatedAtDesc(String username);

    default Schedule findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(() -> new MyCustomException(ErrorCode.POST_NOT_FOUND));
    }

    @Query("""
                SELECT new org.example.scheduledevelop.schedule.dto.ScheduleResponseDto(
                    s.id,
                    new org.example.scheduledevelop.users.dto.UserInfoDto(s.user.id, s.user.username),
                    s.title,
                    s.contents,
                    s.createdAt,
                    s.updatedAt,
                    COUNT(c.id)
                )
                FROM Schedule s
                LEFT JOIN Comment c ON c.schedule.id = s.id
                WHERE (:username IS NULL OR s.user.username = :username)
                GROUP BY s.id, s.user.id, s.user.username, s.title, s.contents, s.createdAt, s.updatedAt
            """)
    Page<ScheduleResponseDto> findAllWithCommentCountAndUsername(
            @Param("username") String username,
            Pageable pageable
    );
}
