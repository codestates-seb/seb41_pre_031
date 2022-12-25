package com.codestates.backend.pre_project.post.question.repository;

import com.codestates.backend.pre_project.post.question.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
