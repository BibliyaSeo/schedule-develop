package org.example.scheduledevelop.comments.repository;

import org.example.scheduledevelop.comments.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
