package org.example.scheduledevelop.users.service;

import lombok.RequiredArgsConstructor;
import org.example.scheduledevelop.schedule.repository.ScheduleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final ScheduleRepository scheduleRepository;
}
