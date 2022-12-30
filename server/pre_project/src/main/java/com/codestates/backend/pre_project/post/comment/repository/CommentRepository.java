package com.codestates.backend.pre_project.post.comment.repository;

import com.codestates.backend.pre_project.post.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
