package org.example.scheduledevelop.users.repository;

import org.example.scheduledevelop.users.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByUsernameOrderByCreatedAtAsc(String username);
}
