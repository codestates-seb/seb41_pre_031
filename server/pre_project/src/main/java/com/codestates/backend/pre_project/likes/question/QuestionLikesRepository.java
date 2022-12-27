package com.codestates.backend.pre_project.likes.question;

import com.codestates.backend.pre_project.member.entity.Member;
import com.codestates.backend.pre_project.post.answer.entity.Answer;
import com.codestates.backend.pre_project.post.question.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuestionLikesRepository extends JpaRepository<QuestionLikes, Long> {
    Optional<QuestionLikes> findByMemberAndQuestion(Member member, Question question);
}