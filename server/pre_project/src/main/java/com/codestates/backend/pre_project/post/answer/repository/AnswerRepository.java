package com.codestates.backend.pre_project.post.answer.repository;


import com.codestates.backend.pre_project.post.answer.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
