package org.example.scheduledevelop.schedule.repository;

import org.example.scheduledevelop.schedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findByUsernameOrderByCreatedAtDesc(String username);
}
