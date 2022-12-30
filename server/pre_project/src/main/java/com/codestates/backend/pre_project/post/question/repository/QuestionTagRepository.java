package com.codestates.backend.pre_project.post.question.repository;

import com.codestates.backend.pre_project.post.question.Question;
import com.codestates.backend.pre_project.post.question.QuestionTag;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface QuestionTagRepository extends JpaRepository<QuestionTag, Long> {
    @Transactional
    void deleteByQuestion(Question question);
}
