package com.codestates.backend.pre_project.likes;

import com.codestates.backend.pre_project.member.entity.Member;
import com.codestates.backend.pre_project.post.answer.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AnswerLikesRepository extends JpaRepository<AnswerLikes, Long> {
    Optional<AnswerLikes> findByMemberAndAnswer(Member member, Answer answer);
}